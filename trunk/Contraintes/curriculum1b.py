import os.path
#data = raw_input("Entrez le fichier data : ")
#data=os.path.splitext(data)[0]
#print(data)
data = __import__("data8")
#print(data.s)

######0683959704

from constraint_solver import pywrapcp

model = pywrapcp.Solver('Gestion cursus universitaire')
prereqIndice = []
semestres=[i for i in range(data.s)]

for i in range(len(data.modules)):
    for j in range(len(data.modules)):
        for p in data.prereq:
            if data.modules[i]==p[0] and data.modules[j]==p[1]:
                prereqIndice.append((i,j))

#######################################
#Remplissage de la matrice de solution#
#######################################
solution = [[model.IntVar(0,1,"semestre"+ str(i) + " module "+ data.modules[j]) for j in range(len(data.modules))] for i in range(data.s)]

#Creation de variable sur les ECTS par semestre#
ECTSBySem = [model.IntVar(data.minC, data.maxC) for i in range(data.s)]

#Creation de variable sur le nb de matiere par semestre#
ModBySem = [model.IntVar(data.minM, data.maxM) for i in range(data.s)]

##########################
#Creation des contraintes#
##########################
#La somme de ECTSBySem doit etre egale a la somme des ECTS#
model.Add(sum(data.ECTS) == model.Sum(ECTSBySem))

#La somme de MatBySem doit etre egale au nb de matiere dansl'annee#
model.Add(len(data.modules) == model.Sum(ModBySem))

#Chaque module n'apparait qu'une fois par an
for j in range(len(data.modules)):
    module = [row[j] for row in solution]
    model.Add(model.Count(module,1,1))

#Le nombre de modules par semestres est egale au ModBySem associe 
for i in range(data.s):
    nbModules = model.Sum(solution[i])
    model.Add(ModBySem[i] == nbModules)

#Le nombre d'ECTS doit etre egale au ECTSBySem de on semestre
for i in range(data.s) :
    nbECTS = model.ScalProd(solution[i], data.ECTS)
    model.Add(ECTSBySem[i] == nbECTS)

#Contrainte de precedences sur les modules et les semestres
for p in prereqIndice :
    b= model.BoolVar()
    semestreM1 = [s[p[0]] for s in solution]
    semestreM2 = [s[p[1]] for s in solution]
    model.Add(model.IsGreaterCt(model.ScalProd(semestreM1, semestres), model.ScalProd(semestreM2,semestres), b ))
    model.Add(b==1)

#Contraintes redondantes de precedences sur les modules et les semestres
#Cette methode a ete trouvee par Marcel Teko Hemazro
for p in data.prereq :
    m1 = [row[data.modules.index(p[1])] for row in solution]
    m2 = [row[data.modules.index(p[0])] for row in solution]
    for i in range(data.s-1):
        model.Add(model.Sum(m2[i+1:])>=m1[i])

sol=[]
for s in solution :
    sol = sol + s
sol += ModBySem
sol += ECTSBySem
db = model.Phase(sol,
                 model.INT_VAR_DEFAULT,
                 model.ASSIGN_MAX_VALUE)

# Recherche
model.NewSearch(db)
if model.NextSolution():
    for i in range(data.s) :
        affiche = ""
        for j in range(len(data.modules)) :
            if (0 != solution[i][j].Value()) :  
                affiche = affiche + data.modules[j]+" "
        print(affiche+"\n")
    print(model.Branches())
    print(model.Failures())
model.EndSearch()
#print(a.modules[1])
#mat =[[2,3],[3,1,2]]
#print(mat)
#print(mat[0][2])
