import numpy as np
import matplotlib.pyplot as plt

'''
Description: Serveral statistics of each column of the input data sets are
             calculated and displayed as box plot.
listOfData:  Each element contains different kinds of data.
fig:         figure object
'''

def boxPlot(listOfData, plotTitle=None, xLabel=None, yLabel=None, savePath=None, display=False):

    # multiple box plots on one figure
    fig = plt.figure()
    plt.boxplot(listOfData)
    if plotTitle is not None :
        plt.title(plotTitle)
    if xLabel is not None :
        plt.xlabel(xLabel)
    if yLabel is not None :
        plt.ylabel(yLabel)
    if savePath is not None :
        plt.savefig("savePath")
    if display :
        plt.show()

    return fig

if __name__ == "__main__" :

    # fake up some data
    spread = np.random.rand(50) * 100
    center = np.ones(25) * 50
    flier_high = np.random.rand(10) * 100 + 100
    flier_low = np.random.rand(10) * -100
    data = np.concatenate((spread, center, flier_high, flier_low), 0)
    listOfData = [data, data]

    boxPlot(listOfData,display=True)

