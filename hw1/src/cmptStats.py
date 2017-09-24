import numpy as np
import libUtils as utl
# import matplotlib.pyplot as plt


if __name__ == "__main__" :
    # set data path
    totalMoves = 1000000
    dataFolder = '../outputs/'
    dataName = ['testRstNum', 'testRstTime']
    dataChoices = [1,2,3,4,5,6]
    listOfStyles = ['-ob', '-*k', '-^r']
    listOfLabels = ['mean', 'standard deviation', 'median']

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

    for index, choice in enumerate(dataChoices) :
        dataPath = dataFolder + dataName[0] + str(choice) + '.csv'
        data = utl.readCSV(dataPath)
        data = [float(ele) for ele in data]

        dataNum[index] = np.array(data)

        numMean[index] = np.mean(dataNum[index])
        numStd[index] = np.std(dataNum[index])
        numMedian[index] = np.median(dataNum[index])

    for index, choice in enumerate(dataChoices) :
        dataPath = dataFolder + dataName[1] + str(choice) + '.csv'
        data = utl.readCSV(dataPath)
        data = [float(ele) for ele in data]

        dataTime[index] = np.array(data)
        dataMovPerSec[index] = np.reciprocal(dataTime[index]) * 1000000
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
            plotTitle='number of soltions found', \
            savePath='../outputs/cmp_num_of_sols_box.png',
            xLabel='Heuristics from 1 to 6', \
            yLabel='Number of closed Knights\' tour found', \
            display=False)

    fig2 = utl.boxPlot(dataTime,\
            plotTitle='time used', \
            savePath='../outputs/cmp_time_used_box.png',
            xLabel='Heuristics from 1 to 6', \
            yLabel='Total time used for each run',\
            display=False)

    fig3 = utl.boxPlot(dataMovPerSec,\
            plotTitle='moves per second', \
            savePath='../outputs/cmp_moves_per_sec_box.png',
            xLabel='Heuristics from 1 to 6', \
            yLabel='Moves per second', display=False)

    fig4 = utl.boxPlot(dataSolsPerSec,\
            plotTitle='solutions per second', \
            savePath='../outputs/cmp_sols_per_sec_box.png',
            xLabel='Heuristics from 1 to 6', \
            yLabel='Solutions per second', display=False)

    fig5 = utl.lineChart([np.array(numMean), np.array(numStd),
        np.array(numMedian)], listOfStyles, listOfLabels, \
            savePath='../outputs/cmp_num_of_sols_line.png',
            plotTitle='mean, std, median of number of solution found', \
            xLabel='Heuristics from 1 to 6', \
            yLabel='Mean, Std, Median of number of solutions found', display=False)

    fig6 = utl.lineChart([np.array(timeMean), np.array(timeStd),
        np.array(timeMedian)], listOfStyles, listOfLabels, \
            savePath='../outputs/cmp_time_used_line.png',
            plotTitle='mean, std, median of time used', \
            xLabel='Heuristics from 1 to 6', \
            yLabel='Mean, Std, Median of time taken', display=False)

    fig7 = utl.lineChart([np.array(movesPerSecMean), np.array(movesPerSecStd),
        np.array(movesPerSecMedian)], listOfStyles, listOfLabels, \
            savePath='../outputs/cmp_moves_per_sec_line.png',
            plotTitle='mean, std, median of moves per secend', \
            xLabel='Heuristics from 1 to 6', \
            yLabel='Mean, Std, Median of moves per second', display=False)

    fig8 = utl.lineChart([np.array(solsPerSecMean), np.array(solsPerSecStd),
        np.array(solsPerSecMedian)], listOfStyles, listOfLabels, \
            savePath='../outputs/cmp_sols_per_sec_line.png',
            plotTitle='mean, std, median of solutions found per secend', \
            xLabel='Heuristics from 1 to 6', \
            yLabel='Mean, Std, Median of solutions found per second', display=False)
