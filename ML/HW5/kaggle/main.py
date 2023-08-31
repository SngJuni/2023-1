import numpy as np
import torch
import torch.nn as nn

from time import time
from torch.utils.data import TensorDataset, DataLoader

from utils import load_data, run
from models import MLP


def assign_weights(labels, confidence_threshold):
    weights = np.zeros_like(labels, dtype=np.float32)
    weights[labels >= confidence_threshold] = 1.0
    weights[labels < confidence_threshold] = 0.5

    return weights


def train_and_predict(train_data, valid_data, test_data):
    """Train a model and return prediction on test images.

    Given train and valid data, build your model and optimize.
    Then, return predictions on test_images.

    You can import packages you want inside 'EDIT HERE' as long as they are permitted.
    (See document for the list of possible packages)

    arguments:
        train_data: tuple of (pandas.DataFrame, np.array).
        - 0: pandas.DataFrame with columns ['id', 'label']
          'id' contains unique id assigned to each image.
          'label' contains label (0 ~ # classes-1) corresponding to its image.
        - 1: train image in np.array of (# train data, # channel, height, width)

        valid_data: tuple of (pandas.DataFrame, np.array).
        - 0: pandas.DataFrame with columns ['id', 'label']
          'id' contains unique id assigned to each image.
          'label' contains label (0 ~ # classes-1) corresponding to its image.
        - 1: valid image in np.array of (# valid data, # channel, height, width)

        test_data: tuple of (pandas.DataFrame, np.array).
        - 0: pandas.DataFrame with columns ['id']
          'id' contains unique id assigned to each image.
        - 1: test image in np.array of (# test data, # channel, height, width)
    
    returns:
        pandas.DataFrame, predictions on test images with columns ['id', 'label'].
        'id' should contain unique id assigned to test images. 
        'label' should contain prediction on the test image correspond to its id

    """

    device = 'cuda' if torch.cuda.is_available() else 'cpu'

    LEARNING_RATE = 0.01
    BATCH_SIZE = 256
    TEST_BATCH_SIZE = 1024
    NUM_EPOCHS = 200
    PATIENCE = 10
    ENDURE = 15

    train_id_label, train_images = train_data
    valid_id_label, valid_images = valid_data
    test_id, test_images = test_data

    num_train = len(train_images)
    num_valid = len(valid_images)
    num_test = len(test_images)
    
    # Convert data into torch.Tensor
    x_train = torch.FloatTensor(train_images.reshape(num_train, -1))
    y_train = train_id_label['label'].values
    y_train = torch.LongTensor(y_train)

    x_valid = torch.FloatTensor(valid_images.reshape(num_valid, -1))
    y_valid = valid_id_label['label'].values
    y_valid = torch.LongTensor(y_valid)

    x_test = torch.FloatTensor(test_images.reshape(num_test, -1))

    num_features = np.prod(x_train.shape[1:])
    num_classes = int(y_train.max()) + 1

    # Build torch dataset, dataloader
    train_dataset = TensorDataset(x_train, y_train)
    valid_dataset = TensorDataset(x_valid, y_valid)
    test_dataset = TensorDataset(x_test)

    train_loader = DataLoader(train_dataset, batch_size=BATCH_SIZE, shuffle=True)
    valid_loader = DataLoader(valid_dataset, batch_size=BATCH_SIZE, shuffle=False)
    test_loader = DataLoader(test_dataset, batch_size=TEST_BATCH_SIZE, shuffle=False)

    # Build MLP model
    model = MLP(num_features, num_classes).to(device)

    # Optimizer and loss function
    optimizer = torch.optim.SGD(model.parameters(), lr=LEARNING_RATE)
    loss_fn = nn.CrossEntropyLoss()


    # Train model
    mean_train_losses = []
    mean_valid_losses = []
    valid_acc_list = []
    best_acc = -1

    train_s = time()
    for epoch in range(1, NUM_EPOCHS + 1):
        epoch_s = time()
        model.train()
        
        train_losses = []
        valid_losses = []
        for i, (images, labels) in enumerate(train_loader):
            images, labels = images.to(device), labels.to(device)
            optimizer.zero_grad()
            
            outputs = model(images)
            loss = loss_fn(outputs, labels)

            weights = assign_weights(labels, confidence_threshold=0.7)
            weighted_loss = (loss * torch.tensor(weights).to(device)).mean()

            weighted_loss.backward()
            optimizer.step()
            
            train_losses.append(weighted_loss.item())
            
        model.eval()
        
        correct = 0
        total = 0
        # Disable gradient calculation for memory, computation efficiency
        with torch.no_grad():
            for i, (images, labels) in enumerate(valid_loader):
                images, labels = images.to(device), labels.to(device)

                outputs = model(images)
                loss = loss_fn(outputs, labels)
                
                valid_losses.append(loss.item())
                
                predicted = torch.argmax(outputs.data, 1)
                correct += (predicted == labels).sum().item()
                total += labels.size(0)
                
        mean_train_losses.append(np.mean(train_losses))
        mean_valid_losses.append(np.mean(valid_losses))

        epoch_elapsed = time() - epoch_s
        
        valid_acc = correct / total
        valid_acc_list.append(valid_acc)
        print('epoch: {}, train loss: {:.4f}, valid loss: {:.4f}, valid acc: {:.4f}, elapsed: {:.4f}'\
            .format(epoch, np.mean(train_losses), np.mean(valid_losses), valid_acc, epoch_elapsed))
        
        if best_acc < valid_acc:
            print('Best Accuracy updated (%.4f => %.4f)' % (best_acc, valid_acc))
            best_acc = valid_acc
            best_epoch = epoch
            ENDURE = 0
            # Save best model
            torch.save(model.state_dict(), 'best_mlp.p')
        else:
            ENDURE += 1
            if ENDURE >= PATIENCE:
                print('Early stop triggered...!')
                break
    train_elapsed = time() - train_s

    print('Training Finished...!!')
    print('Time: %.4f' % train_elapsed)
    print('Best Valid acc : %.4f at epoch %d' % (best_acc, best_epoch))
    
    # Load best model
    model.load_state_dict(torch.load('best_mlp.p'))
    model.eval()

    # Make prediction on test data
    test_preds = []
    with torch.no_grad():
        for i, (images, ) in enumerate(test_loader):
            images = images.to(device)

            outputs = model(images)
            
            pred = outputs.argmax(1)

            if device == 'cuda':
                test_preds += pred.detach().cpu().numpy().tolist()
            else:
                test_preds += pred.detach().numpy().tolist()
    
    # Return prediction
    test_id['label'] = test_preds
    pred = test_id.loc[:, ['id', 'label']]

    return pred


if __name__=='__main__':
    # Load data
    train_data, valid_data, test_data = load_data('data')

    # Data statistics
    print(f'train id label:\n {train_data[0].head()}')
    print(f'train images shape: {train_data[1].shape}\n')
    assert len(train_data[0]) == len(train_data[1])

    print(f'valid id label:\n {valid_data[0].head()}')
    print(f'valid images shape: {valid_data[1].shape}\n')
    assert len(valid_data[0]) == len(valid_data[1])

    print(f'test id:\n {test_data[0].head()}')
    print(f'test images shape: {test_data[1].shape}\n')
    assert len(test_data[0]) == len(test_data[1])

    # 'run' function will grap your prediction and make 'submission.csv'
    run(train_and_predict, train_data, valid_data, test_data)
