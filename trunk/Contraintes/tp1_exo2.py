from constraint_solver import pywrapcp
model = pywrapcp.Solver('Restaurant')
total = 1505
listeValeur = [MF,FF,SS,HW,MS,SP] = [215,275,335,355,420,580]
listeVar = [model.IntVar(0,7) for i in range(6)]

# Constraintes
model.Add(MF*listeVar[0]+FF*listeVar[1]+SS*listeVar[2]+HW*listeVar[3]+MS*listeVar[4]+SP*listeVar[5] == total)

# Create search phases.
vars_phase = model.Phase(listeVar,
                         model.INT_VAR_SIMPLE,
                         model.ASSIGN_RANDOM_VALUE )

model.NewSearch(vars_phase)
while model.NextSolution():
    print(listeVar)
print(model.Failures())
print(model.Branches())
model.EndSearch()

