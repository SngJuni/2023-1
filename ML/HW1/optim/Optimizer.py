import numpy as np

"""
DO NOT EDIT ANY PARTS OTHER THAN "EDIT HERE" !!!

"""

class SGD:
    def __init__(self):
        pass

    def update(self, w, grad, lr):
        grad = gradient_clipping(grad, 10)
        # ==== EDIT HERE ====
        
        updated_weight = None
        updated_weight = w - (grad * lr)

        # ===================
        return updated_weight
    

class Momentum:
    def __init__(self, gamma):
        self.prev_grad = None
        self.gamma = gamma

    def update(self, w, grad, lr):
        grad = gradient_clipping(grad, 10)
        # ==== EDIT HERE ====


        updated_weight = None
        if self.prev_grad is None:
            self.prev_grad = np.zeros_like(w)
        self.prev_grad = (self.gamma * self.prev_grad) - (grad * lr)
        updated_weight = w + self.prev_grad


        # ===================
        return updated_weight
    

def gradient_clipping(grad, threshold):
    """
    Gradient clipping stabilizes learning by clipping the gradient when it exceeds a certain threshold.
    This is usually done by dividing by the L2 norm of the gradient.
    """
    if np.linalg.norm(grad) > threshold:
        grad = grad * (threshold / np.linalg.norm(grad))
    return grad