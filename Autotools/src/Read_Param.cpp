/*
 * Read_Param.cpp
 *
 *  Created on: 11 janv. 2012
 *      Author: alain
 */

#include "Read_Param.h"

using namespace std;


Read_Param::Read_Param() {
	legend ="";
	outnamefile="outfile";
	// TODO Auto-generated constructor stub

}

Read_Param::~Read_Param() {
	// TODO Auto-generated destructor stub
}

void Read_Param::print_usage(  )
{
	cout << "USAGE : "  << endl;
	cout << "-l or --legende  ma_legende"  << endl;
	cout <<	"-n or --outnamefile output_name_file" << endl;
}

void Read_Param::set_Param(int argc,char**argv){

	int c;
	// On parcourt la liste des options
	while (1)
	{
		static struct option long_options[] =
		{
				{"help",  no_argument, 0, 'h'},
				{"legend",  required_argument, 0, 'l'},
				{"outnamefile",  required_argument, 0, 'n'},
				{0, 0, 0, 0}
		};
		//Valeur de l'index des options stockée par getopt_long
		int option_index = 0;

		c = getopt_long (argc, argv, "hl:n:",
				long_options, &option_index);

		// Test de détection de la fin des options */
		if (c == -1)
			break;

		switch (c)
		{
		case 0:
			/* Quit en cas d'erreur de la fonction getopt_long après message d'erreurs*/
			if (long_options[option_index].flag != 0)
				break;
			printf ("option %s", long_options[option_index].name);
			if (optarg)
				printf (" with arg %s", optarg);
			printf ("\n");
			break;

		case 'h':
			print_usage();
			break;
		case 'l':
			//printf ("option -l with value `%s'\n", optarg);
			legend=optarg;
			break;

		case 'n':
			//printf ("option -n with value `%s'\n", optarg);
			outnamefile=optarg;
			break;
		case '?':
			print_usage();
			// getopt_long already printed an error message.
			break;

		default:
			abort ();
		}
	}


}
