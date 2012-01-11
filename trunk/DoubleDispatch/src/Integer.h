#ifndef INTEGER_H_
#define INTEGER_H_

#include "Number.h"

class Integer : public Number {
private:
	int value;

public:
	Integer();
	Integer(int n);
	~Integer();
	int getValue() const;
	Number* add(const Number* n) const;
	Number* add(const Integer* n) const;
};

#endif /*INTEGER_H_*/
