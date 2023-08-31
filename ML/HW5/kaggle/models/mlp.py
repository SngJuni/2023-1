import torch.nn as nn

class MLP(nn.Module):
    def __init__(self, input_dim, output_dim):
        super(MLP, self).__init__()
        self.input_dim = input_dim
        self.output_dim = output_dim
        self.layers = nn.Sequential(
            nn.Linear(self.input_dim, 100),
            nn.ReLU(),
            nn.Linear(100, self.output_dim)
        )
        
    def forward(self, x):
        x = self.layers(x)
        
        return x