from constraint_solver import pywrapcp
model = pywrapcp.Solver('')


ingrid = [[ 1, 1, 0, 0, 0, 1, 0, 0] ,
          [1, 1, 1, 1, 0, 1, 0, 0] ,
          [0, 1, 0, 1, 0, 0, 0, 0] ,
          [0, 1, 0, 1, 1, 0, 1, 0] ,
          [0, 0, 1, 1, 1, 0, 0, 1] ,
          [1, 1, 0, 0, 0, 1, 1, 0] ,
          [0, 0, 0, 1, 0, 1, 1, 0] ,
          [0, 0, 0, 0, 1, 0, 1, 1]]
outgrid = {}



    db = model.Phase(Pates,
                     model.CHOOSE_RANDOM,
                     model.ASSIGN_MAX_VALUE)

    # Recherche
    model.Solve(db,[collector,objectif])
    if collector.SolutionCount() > 0:
        print([collector.Value(0,P) for P in Pates],
              collector.ObjectiveValue(0))
