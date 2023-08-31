import matplotlib.pyplot as plt

from sklearn.svm import SVC
from sklearn.naive_bayes import GaussianNB

from sklearn.datasets import load_iris
from sklearn.inspection import DecisionBoundaryDisplay

from utils.utils import accuracy


def main():
    iris = load_iris()

    # Take only 2 dimensions
    X = iris.data[:, :2] 
    y = iris.target
    
    """
    Choose the hyperparameters for models.
    CAUTION: scikit-learn (>= 1.1.0), Python (>= 3.8) is required to run this code.
    
    Models are listed below.
    - Naive Bayes: GaussianNB()
    - Support Vector Machine: SVC()
    
    Hyperparamters are listed below.
    - Naive Bayes:
        - var_smoothing: float, default=1e-9 (Portion of the largest variance of all features that is added to variances for calculation stability)
    - Support Vector Machine:
        - C: float, default=1.0
        - kernel: str, default='rbf' ('linear', 'poly', 'rbf', 'sigmoid')
        - degree: int, default=3 (degree of polynomial kernel function)
        
    Resources
    ------------
    https://scikit-learn.org/stable/modules/generated/sklearn.naive_bayes.GaussianNB.html#sklearn.naive_bayes.GaussianNB
    https://scikit-learn.org/stable/modules/generated/sklearn.svm.SVC.html#sklearn.svm.SVC
    """
    # ========================= EDIT HERE =========================
    
    # Hyperparameters
    var_smoothing = 1e-1
    C = 100  # C must be positive
    kernel = 'rbf' # 'linear', 'poly', 'rbf', 'sigmoid'
    degree = 3 # degree of polynomial kernel function
    
    # Build model
    models = (
        GaussianNB(var_smoothing=var_smoothing),
        SVC(kernel=kernel, C=C, degree=degree),
    )
    
    # =============================================================
    models = (clf.fit(X, y) for clf in models)

    # title for the plots
    titles = (
        "Naive Bayes Classifier",
        "SVM",
    )

    # Set-up 2x2 grid for plotting.
    _, sub = plt.subplots(2, 1)
    plt.subplots_adjust(wspace=0.4, hspace=0.4)

    X0, X1 = X[:, 0], X[:, 1]

    # Plot the decision boundary
    for clf, title, ax in zip(models, titles, sub.flatten()):
        _ = DecisionBoundaryDisplay.from_estimator(
            clf,
            X,
            response_method="predict",
            cmap="coolwarm",
            alpha=0.5,
            ax=ax,
            xlabel=iris.feature_names[0],
            ylabel=iris.feature_names[1],
        )
        pred = clf.predict(X)
        acc = accuracy(pred, y)
        title += f" (acc: {acc:.2f})"
        ax.scatter(X0, X1, c=y, cmap=plt.cm.coolwarm, s=20, edgecolors="k")
        ax.set_xticks(())
        ax.set_yticks(())
        ax.set_title(title)
    plt.show()
    
    # Save the figure
    plt.savefig(f'model_comparison_results_.png')
    print(f'> Plot saved as model_comparison_results.png')
    

if __name__ == '__main__':
    main()
