/*
 * Node.h
 *
 *  Created on: 24 janv. 2012
 *      Author: E11A932Q
 */

#ifndef NODE_H_
#define NODE_H_

template  < class T > class  Node {
private:
	T& element;
	Node* pere;
	Node* fils;
	Node* frere_suiv;
	Node* frere_prec;


public:
	Node();
	Node(Node* pere);
	Node(Node* pere, Node* frere);
	virtual ~Node();
};

#endif /* NODE_H_ */
