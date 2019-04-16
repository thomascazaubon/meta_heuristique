
class Graph:
    def __init__(self):
        self.edges = []
        self.evacNodes = []

    def add_edge(self, edge):
        self.edges.append(edge)

    def add_evacuation_node(self, evacNode):
        self.evacNodes.append(evacNode)

    def set_safe(self, safe):
        self.safe = safe

    def __str__(self):
        ret = "Number of nodes to be evacuated : " + str(len(self.evacNodes)) + "\n"
        for en in self.evacNodes:
            ret += " *Id:" + str(en.id) + ", " + str(en.pop) + " persons to be evacuated at " + str(en.rate) + "p/T on the following route : "
            for  r in en.route:
                ret += str(r) + " "
            ret += "\n"
        ret += "Number of edges : " + str(len(self.edges)) + "\n"
        for e in self.edges:
            ret += " *" + e.nodeDep + " --> " + e.nodeArr + " DD=" + e.duedate + " len=" + e.length + " cap=" + e.capacity
            ret += "\n"
        return ret

class Edge:
    def __init__(self, nodeDep, nodeArr, dd, len, cap):
        self.nodeDep = nodeDep
        self.nodeArr = nodeArr
        self.duedate = dd
        self.length = len
        self.capacity = cap

class evacNode:
    def __init__(self, id, pop, rate, route):
        self.id = id
        self.pop = pop
        self.rate = rate
        self.route = route

def readSolution(nomSol):
    ret = []
    nodes = []
    totalTime = 0
    fichier = open(nomSol,"r")
    lignes = fichier.readlines()
    nb_nodes = lignes[1]
    #On construit la solution en ajoutant les lignes correspondant aux sommets à évacuer
    for i in range(2, 2 + int(nb_nodes)):
        #[0] = id, [1] = rate, [2] = startTime
        nodes.append(lignes[i].split())
        if i == 2 + int(nb_nodes) - 1:
            totalTime = int(lignes[i + 2])
    ret.append(nodes)
    ret.append(totalTime)
    return ret

def readGraph(nom_file):
    g = Graph()
    fichier = open(nom_file,"r")
    lignes = fichier.readlines()
    firstPart = True
    index = 0
    while index < len(lignes):
        tabLig = lignes[index].split()
        if not firstPart:
            #Ignore la ligne <nb nodes> <nb edges>
            if len(tabLig) != 2:
                #Ajoute une arête par ligne
                g.add_edge(Edge(tabLig[0],tabLig[1],tabLig[2],tabLig[3],tabLig[4]))
        #On a passé la première partie
        if tabLig[0] == 'c' and index != 0:
            firstPart = False
        #Si on est sur la première partie
        if firstPart and tabLig[0] != 'c':
            if len(tabLig) == 2:
                g.set_safe(tabLig[1])
            else:
                g.add_evacuation_node(evacNode(tabLig[0],tabLig[1],tabLig[2],tabLig[4:]))
        index+=1
    fichier.close()
    return g

def resolveSolution(graph, solution):
    for t in range (0, solution[1]):


g = readGraph("exemple1.full")
s = readSolution("solution.txt")
resolveSolution(g,s)
