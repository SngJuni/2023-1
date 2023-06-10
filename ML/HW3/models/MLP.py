import torch 
import numpy as np
import torch.nn as nn
import torch.nn.functional as F 

class MLP_classifier(nn.Module):
    def __init__(self, input_size, num_classes):
        super().__init__()
        self.input_size = input_size
        self.num_classes = num_classes
        # ========================= EDIT HERE =========================
        # Define the layers of the model.

        # Define the activation function.

        # =============================================================
        
    def forward(self, x):
        # ========================= EDIT HERE =========================
        # Given the input x, the function should return the output of the model.
        # Depending on the activation function, the output may vary.
        
        # =============================================================
        return x

    def train(self, train_loader, criterion, optimizer, num_epochs):
        loss = None # loss of final epoch
        # ========================= EDIT HERE =========================
        # Train should be done for 'num_epochs' times.
        # Define the loss function.
        # Define the optimizer.
        # Start training.

        # =============================================================
        
    
    def evaluate(self, test_loader):

        predicted_labels = []
        # ========================= EDIT HERE =========================
        # Evaluate the model on the test set.
        # The function should return the predicted labels of the test set.

        # =============================================================
        
        return predicted_labels
