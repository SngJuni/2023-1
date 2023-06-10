import os
import numpy as np
import pandas as pd

from optim.Optimizer import *
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import MinMaxScaler



def accuracy(h, y):
    """
    h : (N, ), predicted label
    y : (N, ), correct label
    """

    total = h.shape[0]
    correct = len(np.where(h==y)[0])

    acc = correct / total

    return acc


def rel_error(x, y):
  return np.max(np.abs(x - y) / (np.maximum(1e-8, np.abs(x) + np.abs(y))))


def BanknoteData(path, filename):
    df = pd.read_csv(os.path.join(path, filename))
    
    x = df.values[:, :-1].astype(np.float32)
    y = df.values[:, -1].astype(np.int32)
    
    # Add 1 column for bias
    bias = np.ones((x.shape[0], 1), dtype=np.float32)
    x = np.concatenate((bias, x), axis=1)
    
    return x, y


def IrisData(path, filename):
    df = pd.read_csv(os.path.join(path, filename))
    
    x = df.values[:, :-1].astype(np.float32)
    y = df.values[:, -1].astype(np.int32)
    
    # Add 1 column for bias
    bias = np.ones((x.shape[0], 1), dtype=np.float32)
    x = np.concatenate((bias, x), axis=1)
    
    return x, y


def CircleData(df):
    x = df.values[:, :-1].astype(np.float32)
    y = df.values[:, -1].astype(np.int32)
    

    mask = (np.abs(x[:, 0]) <= 100) & (np.abs(x[:, 1]) <= 100)
    x[:, 0] = np.where(mask, x[:, 0], -x[:, 0])

    # Add 1 column for bias
    bias = np.ones((x.shape[0], 1), dtype=np.float32)
    x = np.concatenate((bias, x), axis=1)
    
    scalar = MinMaxScaler()
    x = scalar.fit_transform(x)

    return x, y


data_dir = {
    'Banknote': 'banknote',
    'Iris': 'iris',
    'Circle' : 'circle',
}


def load_data(data_name):
    dir_name = data_dir[data_name]
    path = os.path.join('./data', dir_name)

    if data_name == 'Banknote':
        train_x, train_y = BanknoteData(path, 'train.csv')
        test_x, test_y = BanknoteData(path, 'test.csv')
    elif data_name == 'Iris':
        train_x, train_y = IrisData(path, 'train.csv')
        test_x, test_y = IrisData(path, 'test.csv')
    elif data_name == 'Circle':
        df = pd.read_csv('data/circle/train.csv')
        train_df, test_df = train_test_split(df, test_size=0.3, random_state=42)

        train_x, train_y = CircleData(train_df)
        test_x, test_y = CircleData(test_df)
    else:
        raise NotImplementedError

    return (train_x, train_y), (test_x, test_y)
