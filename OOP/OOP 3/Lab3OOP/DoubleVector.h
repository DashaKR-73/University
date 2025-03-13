#if !defined(DOUBLEVECTOR)
#define DOUBLEVECTOR

#if _MSC_VER > 1000
#pragma once
#endif

class CDoubleVector {
private:
	double* m_pData;
	int m_size;
public:
	void Init(int size);
	CDoubleVector(int size);
	CDoubleVector(const CDoubleVector& data);
	~CDoubleVector();
	int getSize() const;
	void clear();
	double get(int index) const;
	void set(int index, double v);
	double getAvgValue() const;
	double getMin() const;
	double getMax() const;
	double calculate() const;
	double calculate6() const;
};
#endif