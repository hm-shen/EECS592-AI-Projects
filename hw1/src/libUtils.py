import csv
import numpy as np
import matplotlib.pyplot as plt

def readCSV(path, delimitMark=',', quoteMark='|', display=False):
    data = []
    with open(path, 'rb') as csvfile:
        csvReader = csv.reader(csvfile, delimiter=delimitMark,\
                quotechar=quoteMark)
        for ele in csvReader :
            if display:
                print ('data read: {}'.format(ele))
            # concatenate
            data = data + ele
    return data

'''
Description: Serveral statistics of each column of the input data sets are
             calculated and displayed as box plot.
listOfData:  Each element contains different kinds of data.
fig:         figure object
'''

def boxPlot(listOfData, plotTitle=None, xLabel=None, yLabel=None,\
        savePath=None, display=False):

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

    '''
    Test code
    '''
    dataFolder = '../outputs/'
    dataName = ['testRstNum', 'testRstTime']
    dataChoices = [0,1,2,3,4,5,6]
    dataNum = [0] * len(dataChoices)
    dataTime = [0] * len(dataChoices)

    for index in dataChoices :
        dataPath = dataFolder + dataName[0] + str(index) + '.csv'
        data = readCSV(dataPath)
        data = [float(ele) for ele in data]
        dataNum[index] = np.array(data)

    boxPlot(dataNum, plotTitle='Comparison between different heuristics', \
            xLabel='Heuristics from 0 to 6', \
            yLabel='Number of closed Knights\' tour found', display=True)

    # fake up some data
    # spread = np.random.rand(50) * 100
    # center = np.ones(25) * 50
    # flier_high = np.random.rand(10) * 100 + 100
    # flier_low = np.random.rand(10) * -100
    # data = np.concatenate((spread, center, flier_high, flier_low), 0)
    # listOfData = [data, data]

    # boxPlot(listOfData,display=True)
