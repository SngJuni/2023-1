import os
import seaborn as sn
import numpy as np
import pandas as pd
from matplotlib import pyplot as plt

from sklearn.model_selection import train_test_split


def Polynomial(path, filename):
    df = pd.read_csv(os.path.join(path, filename))
    
    x = df.values[:, 0].astype(np.float32).reshape(-1, 1)
    y = df.values[:, 1].astype(np.float32).reshape(-1, 1)
    
    return x, y

def Wine(path, filename): 
    data = pd.read_csv(os.path.join(path, filename), header=None)
    x = data.values[:, 1:].astype(np.float32)
    y = data.values[:, 0].astype(np.int64) - 1 # for zero start

    train_x, test_x, train_y, test_y = train_test_split(x, y, test_size=0.2, random_state=42)
    
    return train_x, train_y, test_x, test_y

data_dir = {
    'Polynomial': 'polynomial',
    'Wine': 'wine',
}


def load_data(data_name):
    dir_name = data_dir[data_name]
    path = os.path.join('./data', dir_name)

    if data_name == 'Polynomial':
        train_x, train_y = Polynomial(path, 'train.csv')
        test_x, test_y = Polynomial(path, 'test.csv')
    elif data_name == 'Wine':
        train_x, train_y, test_x, test_y = Wine(path, 'wine.data')
    else:
        raise NotImplementedError

    return (train_x, train_y), (test_x, test_y)

def accuracy(h, y):
    """
    h : (N, ), predicted label
    y : (N, ), correct label
    """

    total = h.shape[0]
    correct = len(np.where(h==y)[0])

    acc = correct / total

    return acc