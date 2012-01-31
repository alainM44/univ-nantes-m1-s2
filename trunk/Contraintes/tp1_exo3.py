from constraint_solver import pywrapcp

model = pywrapcp.Solver('ECLIPse')
v1 = [0,0,8,2,6,4,5,3,7,0,0]
v2 = [0,0,7,1,6,3,4,5,2,7,0,0]
nlig=len(v1)
ncol=len(v2)
matrix = [[model.IntVar(0,1,'M[{0},{1}]'.format(i,j)) for j in range(ncol)] for i in range(nlig)]


#Contraintes Lignes
for i in range(nlig) :
    li = matrix[i]
    model.Add(model.Count(li,1,v1[i]))

#Contraintes Lignes
for j in range(ncol) :
    cj = [matrix[i][j] for i in range(nlig)]
    model.Add(model.Count(cj,1,v2[j]))

# Create search phases.
mat = []
for i in range(nlig):
    mat = mat+ matrix[i]
vars_phase = model.Phase(mat,
                         model.INT_VAR_SIMPLE,
                         model.INT_VALUE_SIMPLE)

matR=[[""]*ncol for foo in range(nlig)]
model.NewSearch(vars_phase)
while model.NextSolution() :
    for i in range(nlig):
        for j in range(ncol):
            if matrix[i][j].Value() == 1:
                print "*",
            else:
                print " ",
        print

