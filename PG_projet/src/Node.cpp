/*
 * Node.cpp
 *
 *  Created on: 24 janv. 2012
 *      Author: E11A932Q
 */

#include "Node.h"

Node::Node() {
	// TODO Auto-generated constructor stub

}
Node::Node(Node* pere){
	this.pere=pere;
}
Node::Node(Node* pere, Node* frere){
	this.pere=pere;
	this.frere=frere;
}

Node::~Node() {

}
