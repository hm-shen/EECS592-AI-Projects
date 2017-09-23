import numpy as np
import libUtils as utl
import matplotlib.pyplot as plt


if __name__ == "__main__" :
    # set data path
    totalMoves = 1000000
    dataFolder = '../outputs/'
    dataName = ['testRstNum', 'testRstTime']
    dataChoices = [0,1,2,3,4,5,6]

    dataNum = [0] * len(dataChoices)
    numMean = [0] * len(dataChoices)
    numStd = [0] * len(dataChoices)
    numMedian = [0] * len(dataChoices)

    dataTime = [0] * len(dataChoices)
    timeMean = [0] * len(dataChoices)
    timeStd = [0] * len(dataChoices)
    timeMedian = [0] * len(dataChoices)

    dataMovPerSec = [0] * len(dataChoices)
    movesPerSecMean = [0] * len(dataChoices)
    movesPerSecStd = [0] * len(dataChoices)
    movesPerSecMedian = [0] * len(dataChoices)

    dataSolsPerSec = [0] * len(dataChoices)
    solsPerSecMean = [0] * len(dataChoices)
    solsPerSecStd = [0] * len(dataChoices)
    solsPerSecMedian = [0] * len(dataChoices)

    for index in dataChoices :
        dataPath = dataFolder + dataName[0] + str(index) + '.csv'
        data = utl.readCSV(dataPath)
        data = [float(ele) for ele in data]

        dataNum[index] = np.array(data)

        numMean[index] = np.mean(dataNum[index])
        numStd[index] = np.std(dataNum[index])
        numMedian[index] = np.median(dataNum[index])

    for index in dataChoices :
        dataPath = dataFolder + dataName[1] + str(index) + '.csv'
        data = utl.readCSV(dataPath)
        data = [float(ele) for ele in data]

        dataTime[index] = np.array(data)
        dataMovPerSec[index] = np.reciprocal(dataTime[index])
        dataSolsPerSec[index] = dataNum[index] / dataTime[index]

        timeMean[index] = np.mean(dataTime[index])
        timeStd[index] = np.std(dataTime[index])
        timeMedian[index] = np.median(dataTime[index])

        movesPerSecMean[index] = np.mean(dataMovPerSec[index])
        movesPerSecStd[index] = np.std(dataMovPerSec[index])
        movesPerSecMedian[index] = np.median(dataMovPerSec[index])

        solsPerSecMean[index] = np.mean(dataSolsPerSec[index])
        solsPerSecStd[index] = np.std(dataSolsPerSec[index])
        solsPerSecMedian[index] = np.median(dataSolsPerSec[index])

    fig1 = utl.boxPlot(dataNum,\
            plotTitle='Comparison between different Heuristics (number of soltions found)', \
            xLabel='Heuristics from 0 to 6', \
            yLabel='Number of closed Knights\' tour found', display=False)

    fig2 = utl.boxPlot(dataTime,\
            plotTitle='Comparison between different Heuristics (time used)', \
            xLabel='Heuristics from 0 to 6', \
            yLabel='Total time used for each run', display=False)

    fig3 = utl.boxPlot(dataMovPerSec,\
            plotTitle='Comparison between different Heuristics (moves per second)', \
            xLabel='Heuristics from 0 to 6', \
            yLabel='Moves per second', display=False)

    fig4 = utl.boxPlot(dataMovPerSec,\
            plotTitle='Comparison between different Heuristics (solutions per second)', \
            xLabel='Heuristics from 0 to 6', \
            yLabel='Solutions per second', display=False)

    plt.show()

