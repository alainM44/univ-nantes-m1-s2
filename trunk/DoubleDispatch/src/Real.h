#ifndef REAL_H_
#define REAL_H_

#include "Number.h"
class Integer;

class Real : public Number
{
private:
	float value;
public:
	Real(float n);
	Real();
	virtual ~Real();
	void printValue() const;
	float getValue() const;
	Number* add(const Number* n) const;
	Number* add(const Integer* n) const;
	Number* add(const Real * n) const;
};

#endif /*REAL_H_*/
