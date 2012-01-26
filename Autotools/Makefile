
BIN_DIR =	bin/
OBJ_DIR =	obj/
SRC_DIR =	src/
INC_DIR =	include/sources

#files
BIN =	${BIN_DIR}
SRC =	${wildcard ${SRC_DIR}*.cpp}
OBJ =   ${SRC:${SRC_DIR}%.cpp=${OBJ_DIR}%.o}

#Flag
CFLAGS=-W -Wall -ansi -pedantic

#Compiler
CC=g++

#rules
all : ${BIN}

${BIN} : 


clean:
	-rm *.o