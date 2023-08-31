import os
import numpy as np

from utils.utils import CSVReader, split_to_x_y
from models.NaiveBayesClassifier import GaussianNaiveBayes

np.random.seed(100)


def main():
    # ========================= EDIT HERE =========================
    '''
    Choose dataset to use.

    [Stroke]
    For the Stroke dataset, we will use the following features:
    - Input features: ['gender', 'age', 'hypertension', 'heart_disease', 'ever_married', 'work_type',
        'Residence_type', 'avg_glucose_level', 'bmi', 'smoking_status']
    - Output feature: ['stroke']

    [Diabetes]
    For the Diabetes dataset, we will use the following features:
    - Input features: ['gender', 'age', 'hypertension', 'heart_disease', 'smoking_history', 'bmi',
        'HbA1c_level', 'blood_glucose_level']
    - Output feature: ['diabetes']
    
    '''
    DATA_NAME = 'Diabetes' # 'Stroke' or 'Diabetes'
    # =============================================================
    
    assert DATA_NAME in ['Stroke', 'Diabetes']
    
    data_dir = {
        'Carseats': 'carseats',
        'Heart': 'heart',
        'Stroke': 'stroke',
        'Diabetes': 'diabetes'
    }
    
    # Load data
    dir_name = data_dir[DATA_NAME]
    path = os.path.join('./data', dir_name)
    
    data_reader = CSVReader(os.path.join(path, 'train.csv'), 'utf-8')
    train_data = data_reader.df.dropna()

    data_reader = CSVReader(os.path.join(path, 'test.csv'), 'utf-8')
    test_data = data_reader.df.dropna()
    
    # Split data
    train_x, train_y = split_to_x_y(train_data, train_data.columns.values)
    test_x, test_y = split_to_x_y(test_data, test_data.columns.values)
    num_data, num_features = train_x.shape
    
    print(f"> Dataset : {DATA_NAME}")
    print(f"> Number of data: {num_data}, Number of features: {num_features}")
    
    # Build model
    model = GaussianNaiveBayes(DATA_NAME, num_features, num_data)
    
    # Train model
    model.fit(train_data)

    # Predict on train data
    pred_y = model.predict(train_x)
    train_y = train_y.values
    train_acc = np.sum((pred_y == train_y)) / len(train_y)
    print(f'Accuracy on Train Data : {train_acc:.4f}')

    # Predict on test data
    pred_y = model.predict(test_x)
    test_y = test_y.values
    test_acc = np.sum((pred_y == test_y)) / len(test_y)
    print(f'Accuracy on Test Data : {test_acc:.4f}')
    
    # from IPython import embed; embed()


if __name__ == '__main__':
    main()
