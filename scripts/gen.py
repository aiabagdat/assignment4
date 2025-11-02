import json, random, sys

n = int(sys.argv[1])
p = float(sys.argv[2])
cyc = int(sys.argv[3])

g = {"directed": True, "n": n, "edges": [], "source": 0, "weight_model": "edge"}

for u in range(n-1):
    g["edges"].append({"u": u, "v": u+1, "w": random.randint(1, 10)})

for u in range(n):
    for v in range(n):
        if u!=v and random.random()<p:
            if not cyc and v<=u: continue
            g["edges"].append({"u":u,"v":v,"w":random.randint(1,10)})

if cyc:
    g["edges"].append({"u":1,"v":0,"w":2})
    g["edges"].append({"u":2,"v":1,"w":3})
    g["edges"].append({"u":0,"v":2,"w":4})

print(json.dumps(g, indent=2))

