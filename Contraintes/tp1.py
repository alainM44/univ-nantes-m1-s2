from constraint_solver import pywrapcp

model = pywrapcp.Solver('sendmoremoney')
listeVarA = [E,N,D,O,R,Y] = [model.IntVar(0,9) for i in range(6)]
listeVarB = [S,M] =  [model.IntVar(1,9) for i in range(2)]
listeVar = listeVarA+listeVarB

model.Add(model.AllDifferent(listeVar))
model.Add(M*10**4 + O*10**3 + N*10**2 + E*10 + Y== S*10**3 + E*10**2 + N*10 + D + M*10**3 + O*10**2 + R*10 + E)

# Create search phases.
vars_phase = model.Phase(listeVar,
                         model.INT_VAR_SIMPLE,
                         model.INT_VALUE_SIMPLE)

solution = model.Assignment()
solution.Add(listeVar)
collector = model.FirstSolutionCollector(solution)

# And solve.
model.Solve(vars_phase, [collector])

for i in range( 0,collector.solution_count()):
    print collector.solution(i)


#if __name__ == '__main__':
#    main("cp sample")

