import os.path
import sys



def curriculum(data):
    from constraint_solver import pywrapcp

    model = pywrapcp.Solver('Gestion cursus universitaire')
    prereqIndice = []
    semestres=[i for i in range(data.s)]
    ligneDeUn = [1 for i in range(data.s)]

    for i in range(len(data.modules)):
        for j in range(len(data.modules)):
            for p in data.prereq:
                if data.modules[i]==p[0] and data.modules[j]==p[1]:
                    prereqIndice.append((i,j))

#######################################
#Remplissage de la matrice de solution#
#######################################
    solution = [[model.IntVar(0,1,"semestre"+ str(i) + " module "+ data.modules[j]) for j in range(len(data.modules))] for i in range(data.s)]

##########################
#Creation des contraintes#
##########################

#Chaque module n'apparait qu'une fois par an
    for j in range(len(data.modules)):
        module = [row[j] for row in solution]
#Il faut rappeler qu'il ne peut y avoir de valeur negatives dans modules
        model.Add(model.ScalProd(module, ligneDeUn) == 1)

#Le nombre de modules par semestres est entre minM et maxM
    for i in range(data.s):
        nbModules = model.Sum(solution[i])
        model.Add(nbModules >= data.minM)
        model.Add(nbModules <= data.maxM)

#Le nombre d'ECTS doit etre borne entre minC et MaxC
    for i in range(data.s) :
        nbECTS = model.ScalProd(solution[i], data.ECTS)
        model.Add(nbECTS >= data.minC)
        model.Add(nbECTS <= data.maxC)


#Contrainte de precedences sur les modules et les semestres
#Cette methode a ete trouvee par Marcel Teko Hemazro
    for p in data.prereq :
        m1 = [row[data.modules.index(p[1])] for row in solution]
        m2 = [row[data.modules.index(p[0])] for row in solution]
        for i in range(data.s-1):
            model.Add(model.Sum(m2[i+1:])>=m1[i])

    sol=[]
    for s in solution :
        sol = sol + s
    db = model.Phase(sol,
                     model.INT_VAR_DEFAULT,
                     model.ASSIGN_MIN_VALUE)

# Recherche
    model.NewSearch(db)
    if model.NextSolution():
        for i in range(data.s) :
            affiche = ""
            for j in range(len(data.modules)) :
                if (0 != solution[i][j].Value()) :  
                    affiche = affiche + data.modules[j]+" "
            print(affiche+"\n")
        print 'branches ', model.Branches()
        print 'failures ', model.Failures()
        print('times ', model.WallTime())
    model.EndSearch()

if __name__ == '__main__':
    data=__import__(sys.argv[1])
    curriculum(data)
