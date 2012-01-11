#include "Integer.h"

Integer::Integer(int n) 
{ 
	value = n;
}
Integer::Integer() 
{
}

Integer::~Integer() {
}
int Integer::getValue() const
{
	return value;
}
Number* Integer::add(const Number* n) const
{
	return n->add(this);
}

Number* Integer::add(const Integer* n) const
{
	return new Integer(this->value +  n->getValue());
}