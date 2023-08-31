import pandas as pd
import numpy as np

class CSVReader:
    def __init__(self, FileName, encoding):
        self.df = pd.read_csv(FileName, engine='python', encoding=encoding)
        self.header = self.df.columns.values
        self.data = self.df.values

    def pack_data(self):
        data = pd.DataFrame(self.data, columns=self.header)
        return data


def split_to_x_y(df, feature):
    input_feature = feature[:-1]
    class_label = feature[-1]

    X = df[input_feature]
    Y = df[class_label]

    return X, Y

def accuracy(h, y):
    """
    h : (N, ), predicted label
    y : (N, ), correct label
    """

    total = h.shape[0]
    correct = len(np.where(h==y)[0])

    acc = correct / total

    return acc
