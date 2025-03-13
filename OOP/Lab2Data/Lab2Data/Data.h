#ifndef DATA_H
#define DATA_H

class CData {
private:
    double m_a;  

public:
    
    CData();

    CData(const CData& d);

    ~CData();

    void setA(double a);

    double getA() const;

    bool checkA(double min, double max) const;
};

#endif

