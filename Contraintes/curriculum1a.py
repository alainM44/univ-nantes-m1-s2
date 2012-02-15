import os.path
#data = raw_input("Entrez le fichier data : ")
#data=os.path.splitext(data)[0]
#print(data)
data = __import__("data8")
#print(data.s)


from constraint_solver import pywrapcp

model = pywrapcp.Solver('Gestion cursus universitaire')

semestres={}
ECTS = {}

#Remplissage des dictionnaires et création des variables
for i in range(len(data.modules)):
    semestres[data.modules[i]] = model.IntVar(0,7,data.modules[i])
    ECTS[data.modules[i]] = data.ECTS[i]


print(ECTS)
#print(a.modules[1])
#mat =[[2,3],[3,1,2]]
#print(mat)
#print(mat[0][2])
