#include "data.h"
#include <iostream>

using namespace std;

CData::CData(){
    m_a = 0.0;
    cout << "Default constructor" << std::endl;
}

CData::CData(const CData& d) {
    m_a = d.getA();
    cout << "Copy constructor" << std::endl;
}

CData::~CData() {
    cout << "Destructed" << std::endl;
}

void CData::setA(double a) {
    m_a = a;
}

double CData::getA() const {
    return m_a;
}

bool CData::checkA(double min, double max) const {
    return (m_a >= min && m_a <= max);
}