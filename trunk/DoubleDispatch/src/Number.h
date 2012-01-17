#ifndef NUMBER_H_
#define NUMBER_H_

class Integer;
class Real;

class Number
{
public:
	Number();
	virtual ~Number();
	virtual void printValue() const =0;

	virtual Number* add(const Number* n) const =0;
	virtual Number* add(const Integer* n) const =0;
	virtual Number* add(const Real* n) const =0;


};

#endif /*NUMBER_H_*/
