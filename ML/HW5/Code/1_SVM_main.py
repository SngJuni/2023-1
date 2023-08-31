import numpy as np
import matplotlib.pyplot as plt

from utils.utils import accuracy

from sklearn.svm import SVC
from sklearn.datasets import load_iris

def main():
    iris = load_iris()

    # Take only 2 dimensions
    X = iris.data[:, :2] 
    y = iris.target

    # ========================= EDIT HERE =========================
    """
    Choose the regularization term C and kernel function for Support Vector Machine.
    - C: float (default=1.0)
    - kernel: 'linear', 'poly', 'rbf', 'sigmoid' (default=rbf)
    
    """
    C = 0.5  # SVM regularization parameter (C must be positive)
    kernel = 'rbf' # 'linear', 'poly', 'rbf', 'sigmoid'
    degree = 3 # degree of polynomial kernel function
    # =============================================================
    
    model = SVC(kernel=kernel, C=C, degree=degree)
    y_pred = model.fit(X, y).predict(X)
    
    train_acc = accuracy(y_pred, y)
    print(f'> Model: SVM')
    print(f'> C: {C}')
    print(f'> kernel: {kernel}\n')
    print(f'> Training done!')
    print(f'> Accuracy on Train Data : {train_acc:.4f}')
    
    """
    Draw the plot of the decision boundary

    Resources
    ------------
    Scikit-learn: https://scikit-learn.org/stable/modules/svm.html#svm-mathematical-formulation
    Scikit-learn: https://scikit-learn.org/stable/auto_examples/svm/plot_iris_svc.html#sphx-glr-auto-examples-svm-plot-iris-svc-py
    """
    
    x_min, x_max = X[:, 0].min() - 1, X[:, 0].max() + 1
    y_min, y_max = X[:, 1].min() - 1, X[:, 1].max() + 1
    h = (x_max / x_min)/100
    
    xx, yy = np.meshgrid(np.arange(x_min, x_max, h), np.arange(y_min, y_max, h))
    zz = model.predict(np.c_[xx.ravel(), yy.ravel()])
    zz = zz.reshape(xx.shape)
    plt.figure()
    
    plt.contourf(xx, yy, zz, alpha=0.5, cmap="coolwarm")
    plt.scatter(X[:, 0], X[:, 1], c=y, cmap="coolwarm", linewidth=1, edgecolor='black')
    plt.title(f'SVM (C={C}, kernel={kernel}) (ACC: {train_acc:.4f})')
    plt.xlabel('Sepal length')
    plt.ylabel('Sepal width')
    plt.savefig(f'SVM_{kernel}_{C}_results.png')
    print(f'> Plot saved as SVM_{kernel}_{C}_results.png')
    

    
if __name__ == '__main__':
    main()
