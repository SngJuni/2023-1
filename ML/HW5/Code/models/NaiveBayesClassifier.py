import numpy as np
from IPython import embed

category_feature_idx_dict = {
    'Heart': [1, 2, 5, 6, 8, 10, 11, 12],
    'Carseats': [6, 9],
    'Stroke': [0, 2, 3, 4, 5, 6, 9],
    'Diabetes': [0, 1, 2, 3, 4]
    }

class GaussianNaiveBayes():
    def __init__(self, data_name, num_features, num_data):
        self.data_name = data_name
        self.category_feature_idx = category_feature_idx_dict[data_name]
        self.num_features = num_features
        self.num_data = num_data

    def _get_prior(self, data):
        '''
        Inputs:
        data : data to calculate prior
            - Type: pandas.core.series.Series
            - Shape: (# of data,) 
        
        Returns:
        prior_yes : P(Yes), the prior probability of class 'Yes'
        prior_no : P(No), the prior probability of class 'No'

        Description:
        Given the data, calculate the prior probability of class 'Yes' and 'No'
        '''
        # ========================= EDIT HERE =========================

        num_yes = np.sum(data == 'Yes')
        num_no = np.sum(data == 'No')
        prior_yes = num_yes / len(data)
        prior_no = num_no / len(data)

        # =============================================================
        return prior_yes, prior_no
    
   
    def fit(self, train_data):
        '''
        Inputs:
        train_data : training data
            - Type: pandas.core.series.Series
            - Shape: (# of data, # of features + 1)   
                  
        Returns:
        None
        
        Description:
        In this function, we will fit the model to train_data.
        The part that you implement is the probability. P(X | class) and P(class)
        P(X | class) is implemented by using a dictionary.
        
        Hint:
        'prob_given_yes' and 'prob_given_no' are the dictionary that contains the probability of each feature given the class 'Yes' and 'No'.

        prob_given_yes, prob_given_no
            (1) For categorical feature, { {feature_name}_{attribute_name} : probability }
            (2) For numerical feature, { {feature_name}_mean : mean, {feature_name}_std : std }
            
        - Example
            (1) For categorical feature,  'Urban_No': 0.1111, 'Urban_Yes': 0.1111
            (2) For numerical feature, 'Income_mean': 0.1111, 'Income_std': 0.1111
        
        '''
        
        self.df = train_data
        
        # Get names of features
        input_features = self.df.columns.values[:-1]
        output_feature = self.df.columns.values[-1]

        # Divide data into two classes
        pos_data = self.df[self.df[output_feature] == 'Yes']
        neg_data = self.df[self.df[output_feature] != 'Yes']
        
        '''
        Prior probability
        '''
        self.prior_yes, self.prior_no = self._get_prior(self.df[output_feature])


        '''
        Conditional probability 
        '''
        self.prob_given_yes, self.prob_given_no = {}, {}
        self.num_of_attr = [ 0 ] * self.num_features

        for idx, fname in enumerate(input_features):

            # Count the number of attributes for each column for category features
            if idx in self.category_feature_idx:
                attribute, count = np.unique(self.df[fname], return_counts=True)
                
                for attr, cnt in zip(attribute, count):
                    attr = str(int(attr)) if type(attr) == np.float64 else attr
                    
                    self.prob_given_yes[f'{fname}_{attr}'] = 0
                    self.prob_given_no[f'{fname}_{attr}'] = 0
                    
                self.num_of_attr[idx] = len(attribute)


            # P(x_i | Yes)
            pos_col_data = pos_data[fname]

            if idx in self.category_feature_idx:
                pos_attr, pos_count = np.unique(pos_col_data, return_counts=True)

                for attr, cnt in zip(pos_attr, pos_count):
                    attr = str(int(attr)) if type(attr) == np.float64 else attr
                        
                    # ========================= EDIT HERE =========================
                    # Hint: Don't forget to use Laplacian correction
                    
                    prob = (cnt + 1) / (len(pos_col_data) + self.num_of_attr[idx])

                    # =============================================================
                    self.prob_given_yes[f'{fname}_{attr}'] = round(prob, 4)
            else:
                # ========================= EDIT HERE =========================
                
                mean = np.mean(pos_col_data)
                std = np.std(pos_col_data)

                # =============================================================
                self.prob_given_yes[f'{fname}_mean'] = round(mean, 4)
                self.prob_given_yes[f'{fname}_std'] = round(std, 4)


            # P(x_i | No)
            neg_col_data = neg_data[fname]

            if idx in self.category_feature_idx:
                neg_attr, neg_count = np.unique(neg_col_data, return_counts=True)

                for attr, cnt in zip(neg_attr, neg_count):
                    attr = str(int(attr)) if type(attr) == np.float64 else attr
                    # ========================= EDIT HERE =========================
                    # Hint: Don't forget to use Laplacian correction
                    
                    prob = (cnt + 1) / (len(neg_col_data) + self.num_of_attr[idx])

                    # =============================================================
                    self.prob_given_no[f'{fname}_{attr}'] = round(prob, 4)
            else:
                # ========================= EDIT HERE =========================
                
                mean = np.mean(neg_col_data)
                std = np.std(neg_col_data)

                # =============================================================
                self.prob_given_no[f'{fname}_mean'] = round(mean, 4)
                self.prob_given_no[f'{fname}_std'] = round(std, 4)


    def predict(self, test_x):
        '''
        Inputs:
        test_x : test data
            - Type: pandas.core.series.Series
            - Shape: (# of data, # of features)   
                  
        Returns:
        None
        
        Description:
        In this function, make the prediction for test data using self.prob_given_yes and self.prob_given_no.
        The part you will implement is to calculate the likelihood. (P(Yes | X) and P(No | X))
        If you encounter the undefined key of the dictionary (value is 0), you should deal with that using Laplacian.
        
        Hint:
        Use self.prob_given_yes and self.prob_given_no
        '''
        
        # Make empty array for prediction
        pred = np.full(len(test_x), None)
        input_features = test_x.columns.values

        # For each test sample
        for i, test_idx in enumerate(test_x.index):
            row = test_x.loc[test_idx]
            yes_posterior, no_posterior = 1, 1

            # For each feature
            for feature_idx, fname in enumerate(input_features):
                val = row[fname]
                
                # Categorical feature
                if feature_idx in self.category_feature_idx:
                    val = str(int(val)) if type(val) == np.float64 else val
                    
                    # ========================= EDIT HERE =========================
                    # Get the probability of the feature value given the class
                    
                    yes_prob = self.prob_given_yes.get(f'{fname}_{val}', 0)
                    no_prob = self.prob_given_no.get(f'{fname}_{val}', 0)

                    # If the key is not in the dict, use Laplacian correction
                    
                    if yes_prob == 0:
                        yes_prob = 1 / (len(self.df) + self.num_of_attr[feature_idx])
                    if no_prob == 0:
                        no_prob = 1 / (len(self.df) + self.num_of_attr[feature_idx])

                    # =============================================================
                    
                # Numerical feature
                else:
                    # ========================= EDIT HERE =========================
                    # Get the mean and std of the feature given the class
                    
                    yes_mean = self.prob_given_yes.get(f'{fname}_mean', 0)
                    yes_std = self.prob_given_yes.get(f'{fname}_std', 1)
                    no_mean = self.prob_given_no.get(f'{fname}_mean', 0)
                    no_std = self.prob_given_no.get(f'{fname}_std', 1)

                    # =============================================================
                    
                    # Calculate the probability of the feature value given the class using Gaussian distribution
                    yes_prob = self._gaussian_prob(float(val), yes_mean, yes_std)
                    no_prob = self._gaussian_prob(float(val), no_mean, no_std)
                    
                # ========================= EDIT HERE =========================
                # Get posterior using Bayes rule - Likelihood
                
                yes_posterior *= yes_prob
                no_posterior *= no_prob

                # =============================================================

            # ========================= EDIT HERE =========================
            # Get posterior using Bayes rule - Prior probability
            
            yes_posterior *= self.prior_yes
            no_posterior *= self.prior_no
            
            # =============================================================

            # Final prediction
            pred[i] = 'No' if yes_posterior < no_posterior else 'Yes'

        return pred
    
    def _gaussian_prob(self, x, mean, std):
        '''
        Inputs:
        x : value of feature
        mean : mean of x
        std : standard deviation of x
        
        Returns:
        prob : p(x) ~ N(μ, σ^2) (probability of x given mean and std)
        
        Description:
        Calculate the probability of x given mean and std using Gaussian distribution.
        '''
        ret = 1
        # ========================= EDIT HERE =========================

        ret = (1 / (np.sqrt(2 * np.pi) * std)) * np.exp(-((x - mean) ** 2) / (2 * std ** 2))

        # =============================================================
        return ret