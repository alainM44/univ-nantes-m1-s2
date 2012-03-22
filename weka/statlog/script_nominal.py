dst = open("german.arff", "w");
src = open("german.data", "r");
dst.write("@relation german\n")
dst.write("@ATTRIBUTE stat_account {A11,A12,A13,A14}\n")  
dst.write("@ATTRIBUTE duration	 REAL\n")  
dst.write("@ATTRIBUTE credit_history {A30,A31,A32,A33,A34}\n")  
dst.write("@ATTRIBUTE purpose	{A40,A41,A42,A43,A44,A45,A46,A47,A48,A49,A410}\n")  
dst.write("@ATTRIBUTE credit_amount REAL\n")  
dst.write("@ATTRIBUTE savings_account {A61 A62 A63 A64 A65}\n")  
dst.write("@ATTRIBUTE present_employment {A71,A72,A73,A74,A75}\n")  
dst.write("@ATTRIBUTE disposable_income REAL\n")  
dst.write("@ATTRIBUTE personal_status {A91,A92,A93,A94,A95}\n")  
dst.write("@ATTRIBUTE guarantors {A101,A102,A103}\n")  
dst.write("@ATTRIBUTE present_residence REAL\n")  
dst.write("@ATTRIBUTE property {A121,A122,A123,A124}\n")  
dst.write("@ATTRIBUTE age REAL\n")  
dst.write("@ATTRIBUTE installment_plans {A141,A142,A143}\n")  
dst.write("@ATTRIBUTE housing {A151,A152,A153}\n")  
dst.write("@ATTRIBUTE nb_credits REAL\n")  
dst.write("@ATTRIBUTE job {A171,A172,A173,A174}\n")  
dst.write("@ATTRIBUTE nb_people REAL\n")  
dst.write("@ATTRIBUTE telephone {A191,A192}\n")  
dst.write("@ATTRIBUTE foreign_worker {A201,A202}\n")  
dst.write("@attribute class {1,2}\n")  
dst.write("@data\n")  



for ligne in src:
    dst.write(ligne.replace(" ", ","))
