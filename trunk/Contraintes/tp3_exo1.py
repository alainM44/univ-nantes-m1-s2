from constraint_solver import pywrapcp
model = pywrapcp.Solver('Usine a pates')


Farine = [5,4,3]
Oeufs = [2,4,6]
CoutI = [6,8,3]
CoutE = [8,9,4]
Si =model.IntVar(0,4,"Si")
Li =model.IntVar(0,5,"Li")
Ti =model.IntVar(0,6,"Ti")
Se =model.IntVar(0,100,"Se")
Le =model.IntVar(0,200,"Le")
Te =model.IntVar(0,300,"Te")
PatesI = [Si, Li,Ti]
PatesE = [Se, Le,Te]
Pates = PatesI +PatesE

model.Add(model.ScalProd(PatesI, Farine)<=20)
model.Add(model.ScalProd(PatesI, Oeufs)<=40)
model.Add(Si+Se==100)
model.Add(Li+Le==200)
model.Add(Ti+Te==300)
CoutTotal = model.ScalProd(PatesI, CoutI) + model.ScalProd(PatesE, CoutE)
objectif = model.Minimize(CoutTotal,1)
solution = model.Assignment()
solution.Add(Pates)
solution.AddObjective(CoutTotal)

collector = model.LastSolutionCollector(solution)

db = model.Phase(Pates,
                 model.CHOOSE_RANDOM,
                 model.ASSIGN_MAX_VALUE)

# Recherche
model.Solve(db,[collector,objectif])
if collector.SolutionCount() > 0:
    print([collector.Value(0,P) for P in Pates],
          collector.ObjectiveValue(0))
