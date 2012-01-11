/*
 * Read_Param.h
 *
 *  Created on: 11 janv. 2012
 *      Author: alain
 */

#ifndef READ_PARAM_H_
#define READ_PARAM_H_
#include <vector>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <getopt.h>
#include <string>


class Read_Param {
private:
	struct globalArgs_t {
		int noLegend;				/* -I option */
		char *langCode;				/* -l option */
		const char *outFileName;	/* -o option */
	} globalArgs;


public:
	Read_Param();
	std::vector<std::string> Give_Param();
	virtual ~Read_Param();
	void print_usage();
};

#endif /* READ_PARAM_H_ */
