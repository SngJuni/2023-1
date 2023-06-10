import torch
import numpy as np
from models.SoftmaxClassification import SoftmaxClassification
from utils import *

np.random.seed(530)
torch.random.manual_seed(530)

DATA_NAME = 'Wine'

# Load dataset, model and evaluation metric
train_data, test_data = load_data(DATA_NAME)
train_x, train_y = train_data
test_x, test_y = test_data

num_data, num_features = train_x.shape
num_label = int(train_y.max()) + 1

print('# of Training data : %d \n' % num_data)

## These hyperparameters are fixed, do not change them.
batch_size = 64
num_epochs = 2000 
learning_rate = 0.0001

train_results = []
test_results = []

# Build model
model = SoftmaxClassification(num_features, num_label)

model.train(train_x, train_y, batch_size, num_epochs, learning_rate)

################### Evaluate on train data
# Inference
train_acc, _ = model.eval(train_x, train_y, batch_size)
print(f'Accuracy on Train Data : {train_acc:.4f}%')

################### Evaluate on test data
# Inference
test_acc, _ = model.eval(test_x, test_y, batch_size)
print(f'Accuracy on Test Data: {test_acc:.4f}%')



# import SoftmaxClassifier you implemented in HW2
# You must add your 'SoftmaxClassifier.py' in 'models' folder
from optim.Optimizer import SGD
from models.SoftmaxClassifier import SoftmaxClassifier

model = SoftmaxClassifier(num_features = num_features, num_label = num_label)
optim = SGD()

model.train(train_x, train_y, num_epochs, batch_size, learning_rate, optim)

################### Evaluate on train data
# Inference
pred, prob = model.eval(train_x)

train_acc = accuracy(pred, train_y)
print(f'Accuracy on Train Data : {train_acc:.4f}%\n')

################### Evaluate on test data
# Inference
pred, prob = model.eval(test_x)

test_acc = accuracy(pred, test_y)
print(f'Accuracy on Test Data : {test_acc:.4f}%\n')