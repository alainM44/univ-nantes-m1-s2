
entete="@relation german"+"\n"+"@ATTRIBUTE stat_check {A11,A12,A13,A14}"+"\n"+"@ATTRIBUTE duration	 REAL"+"\n"+"@ATTRIBUTE Credit_History {A30,A31,A32,A33,A34}"+"\n"+"@ATTRIBUTE Purpose	{A40,A41,A42,A43,A44,A45,A46,A47,A48,A49,A410}"+"\n"+"@ATTRIBUTE credit_amount REAL"+"\n"+"@ATTRIBUTE Savings_account {A61 A62 A63 A64 A65}"+"\n"+"@ATTRIBUTE present_employment_since{A71,A72,A73,A74,A75}"+"\n"+"@ATTRIBUTE Installment_rate REAL"+"\n"+"@ATTRIBUTE att9 {A91,A92,A93,A94,A95}"+"\n"+"@ATTRIBUTE att10 {A101,A102,A103}"+"\n"+"@ATTRIBUTE att11 REAL"+"\n"+"@ATTRIBUTE att12 {A121,A122,A123,A124}"+"\n"+"@ATTRIBUTE att13 REAL"+"\n"+"@ATTRIBUTE att14 {A141,A142,A143}"+"\n"+"@ATTRIBUTE att15 {A151,A152,A153}"+"\n"+"@ATTRIBUTE att16 REAL"+"\n"+"@ATTRIBUTE att17 {A171,A172,A173,A174}"+"\n"+"@ATTRIBUTE att18 REAL"+"\n"+"@ATTRIBUTE att19 {A191,A192}"+"\n"+"@ATTRIBUTE att20{A201,A202}\n@ATTRIBUTE att21 REAL\n@data\n"




fichier_sortie = open("german.arff", "w");
fichier_entree = open("german.data", "r");
fichier_sortie.write(entete)




for ligne in fichier_entree:
    fichier_sortie.write(ligne.replace(" ", ","))
