#include <iostream>
#include "data.h"

using namespace std;

int main() {
    
    CData* data = new CData();
    char choice;  
    do {
        double value, min, max;

        
        cout << "Input value: ";
        cin >> value;
        data->setA(value);

       
        cout << "Input min value: ";
        cin >> min;
        cout << "Input max value: ";
        cin >> max;

        
        if (data->checkA(min, max)) {
            cout << "Value " << data->getA() << " is in range [" << min << ", " << max << "].\n";
        }
        else {
            cout << "Value " << data->getA() << " isn't in range [" << min << ", " << max << "].\n";
        }
        cout << "Continue? (y/n): ";
        cin >> choice;
    } while (choice == 'y' || choice == 'Y');
    
    delete data;

    return 0;
}


