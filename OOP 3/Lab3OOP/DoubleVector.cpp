#include "DoubleVector.h"
#include <stdio.h>
#include <cmath>

using namespace std;

void CDoubleVector::Init(int size) {
	m_size = size;
	m_pData = new double[m_size];
	for (int i = 0; i < m_size; i++) m_pData[i] = 0;
}

CDoubleVector::CDoubleVector(int size) {
	this->Init(size);
}

CDoubleVector::CDoubleVector(const CDoubleVector& data) {
	m_size = data.getSize();
	m_pData = new double[m_size];
	for (int i = 0; i < m_size; i++) m_pData[i] = data.get(i);
}

CDoubleVector::~CDoubleVector() { clear(); }

int CDoubleVector::getSize() const { return m_size; }

void CDoubleVector::clear() {
	m_size = 0;
	delete[] m_pData;
	m_pData = NULL;
}

double CDoubleVector::get(int index) const { return m_pData[index]; }

void CDoubleVector::set(int index, double v) {
	if ((m_size > 0) && (index < m_size)) m_pData[index] = v;
}

double CDoubleVector::getAvgValue() const {
	double sum = 0;
	for (int i = 0; i < m_size; i++) sum += m_pData[i];
	return sum / (double)(m_size);
}

double CDoubleVector::getMax() const {
	double max = 0;
	for (int i = 0; i < m_size; i++)
		if (m_pData[i] > max)
			max = m_pData[i];
	return max;
}

double CDoubleVector::getMin() const {
	double min = m_pData[0];
	for (int i = 0; i < m_size; i++)
		if (m_pData[i] < min)
			min = m_pData[i];
	return min;
}

double CDoubleVector::calculate() const {
	double s = 0;
	double sum1 = 0;
	double sum2 = 0;
	for (int i = 0; i <= 4; i++) {
		double a1 = sin(abs(1 - log(m_pData[i])));
		sum1 += a1;
	}
	for (int i = 0; i <= 10; i++) {
		double a2 = pow(sin(18 * pow(m_pData[i], 3)), 2);
		sum2 += a2;
	}
	s = sum1 / sum2;
	return s;
}

double fai(int i) {
	double ai = log10((pow(i, 2) + 1) / 30);
	return ai;
}

double CDoubleVector::calculate6() const {
	double mean = 0;
	double sumSquaredDiffs = 0;

	// Calculate the average (ā)
	for (int i = 0; i < m_size && i < 11; i++) {
		mean += m_pData[i];
	}
	mean /= 11;

	
	for (int i = 0; i < m_size && i < 11; i++) {
		double diff = m_pData[i] - mean;
		sumSquaredDiffs += diff * diff;
	}

	double S = sqrt(sumSquaredDiffs / 10); 
	return S;
}