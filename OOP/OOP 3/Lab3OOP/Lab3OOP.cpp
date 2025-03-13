#include <iostream>
#include "DoubleVector.h"
using namespace std;

#define MAX 5
#define CLEAR_CIN cin.ignore(255, '\n')

int main()
{
    int i = 0;
    CDoubleVector* vector = new CDoubleVector(MAX);

    for (i = 0; i < MAX; i++) {
        double v = 0;
        cout << "Enter " << i + 1 << " element: ";
        cin >> v;
        CLEAR_CIN;
        vector->set(i, v);
    }

    for (i = 0; i < MAX; i++) {
        cout << vector->get(i) << " ";
    }

    CDoubleVector test(*vector);
    cout << "\nCopied vector: " << endl;
    for (i = 0; i < MAX; i++) {
        cout << test.get(i) << " ";
    }

    cout << "\nAverage: " << vector->getAvgValue() << endl;
    cout << "Min: " << vector->getMin() << endl;
    cout << "Max: " << vector->getMax() << endl;
    cout << "Calculated function1: " << vector->calculate() << endl;
    cout << "Calculated function2: " << vector->calculate6() << endl;

    delete vector;
    
    return 0;
}
