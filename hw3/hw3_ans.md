---
title: EECS 592 Homework 3
author: Haoming Shen
date: \today
geometry: margin=0.9in
fontsize: 10pt
toc: false
secnumdepth: 0
header-includes:
    - \usepackage{bm}
    - \usepackage{color}
---

\newcommand{\reminder}[1]{{\textsf{\textcolor{red}{[question: #1]}}}}
\newcommand{\ans}[1]{{\textsf{\textcolor{blue}{[ans: #1]}}}}
\newcommand{\eql}{\Leftrightarrow}
\newcommand{\fd}{\Rightarrow}
\newcommand{\ie}{i.e.}

## Problem 1: Evaluating Logical Statements

a) $M := (A \land B) \models S := (A \eql B)$ is **true**:
    1. The assignments for which $(A \land B)$ is true are: $A = 1, B = 1$.
    2. When $A=1, B=1$, $(A \eql B)$ is true.
       i.e.,$A=1,B=1$, $S = (A \eql B)$ is true.
    3. $M \models S$ is true because for all models that evaluate $M$ to be 
       true $S$ is also true.
b) $M := (A \eql B) \models S := (A \lor B)$ is **false**:
    1. Consider $A = 0, B = 0$ which evaluates $M := (A \eql B)$ to be true.
    2. However, $A = 0 \lor B = 0$ is evaluated to be false.
    3. Therefore $M \models S$ is false because there exists an assignment of 
       $A$ and $B$ which makes $M$ to be true and $S$ is false.
c) $M := (A \land B) \fd C \models S := (A \fd C) \lor (B \fd C)$ is **true**:
    1. $S = (\neg A \lor C) \lor (\neg B \lor C) = \neg A \lor \neg B \lor C$.
    2. $M = \neg (A \land B) \lor C = (\neg A \lor \neg B) \lor C = S$.
d) $M := (A \lor B) \land (\neg C \lor \neg D \lor E) \models S := (A \lor B) 
   \land (\neg D \lor E)$ is **false**.
    1. Consider $A = 1, B = 1, C = 0, D = 1, E = 0$ which evaluates $M$ to be 
       true.
    2. However, $S$ is evaluated to be false.
    3. Therefore $M \models S$ is false because there exists an assignment of
       $M$ which makes $M$ to be true but $S$ to be false.
e) $M := (C \lor (\neg A \land \neg B)) \equiv S := ((A \fd C) \land (B \fd 
   C))$ is **true**.
    1. $S \equiv ( \neg A \lor C) \land (\neg B \lor C)$.
    2. $M \equiv (C \lor \neg A) \land (C \lor \neg B)$.
    3. Therefore $M \equiv S$.
f)  The statement, $\alpha \models \beta \eql$ $\alpha \fd \beta$ is valid, is 
    **true**.
    1. Denote $M(\alpha) = \{ x ~ | ~ \alpha(x) = 1 \}$ to be the set of all 
       assignment $x$ such that $\alpha$ is true; $M(\beta)$ is similarly 
       defined.
    2. If $\alpha \models \beta$, then $M(\alpha) \subseteq M(\beta)$. Consider 
       an arbitrary assignment $x_0$. If $x_0 \in M(\alpha)$ then $x_0 \in 
       M(\beta)$, i.e. if $\alpha(x_0) = 1, \beta(x_0) = 1, \alpha \fd \beta 
       \text{ is true, } \forall x_0 \in M(\alpha)$. If $x_0 \not \in 
       M(\alpha)$, then $\alpha(x_0) = 0, \forall x_0 \not \in M(\alpha)$, \ie 
       $\alpha(x_0) \fd \beta(x_0) \text{ is true, } \forall x_0 \not \in 
       M(\alpha)$. Therefore $\alpha(x_0) \fd \beta(x_0),\;  \forall x_0$.
    3. If $\alpha(x_0) \fd \beta(x_0), \; \forall x_0$ then $\alpha(x_0) = 1, 
       \beta(x_0) = 1, \; \forall x_0 \in M(\alpha)$. This implies that $x_0 
       \in M(\beta)$. Therefore $M(\alpha) \subseteq M(\beta)$, \ie $\alpha 
       \models \beta$.
g) The statement, $\alpha \equiv \beta \eql (\neg (\alpha \eql \beta) \text{ is 
   unsatisfiable})$ is **true**.
    1. $\alpha \equiv \beta \eql (\alpha \models \beta) \land (\beta \models 
       \alpha)$. By the conclusion above, $\alpha \equiv \beta \eql (\alpha \fd 
       \beta \text{ is valid}) \land (\beta \fd \alpha \text{ is valid})$.
    2. $\alpha \equiv \beta \eql (\alpha(x) \eql \beta(x), \; \forall x)$. Thus 
       $\alpha \equiv \beta \eql (\neg (\alpha \eql \beta)$ is 
       unsatisfiable$)$.
h) $(\neg B \fd A) \fd (\neg A \fd B)$ is **true**.
    1. $(\neg B \fd A)$ = $\neg (\neg B) \lor A = B \lor A$; $\neg A \fd B = A 
       \lor B$.
    2. Since for all models that make $(\neg B \fd A)$ true, $(\neg A \fd B)$ 
       is also true, the above statement is true.
i) The claim that $(A \land B) \land \neg (A \fd B)$ is satisfiable is **false**.
    1. $\neg (A \fd B) = \neg ( \neg A \lor B) = A \land \neg B$
    2. $(A \land B) \land \neg (A \fd B) = (A \land B) \land (A \land \neg B) = 
       A \land B \land A \land \neg B = A \land B \land \neg B$. This is 
       unsatisfiable.
j) The claim that $(A \eql B) \land (\neg A \lor B)$ is unsatisfiable is 
   **false**.
    1. $(A \eql B) \land (\neg A \lor B) = (A \eql B) \land (A \fd B)$. This is 
       valid for all models.

## Conjunctive Normal Form and Resolution 

### A. Convert the following sentences into CNF

a) $$
\begin{aligned}
D \land \neg B \fd E \\
\neg (D \land \neg B) \lor E \\
\neg D \lor B \lor E
\end{aligned}
$$

b) $$
\begin{aligned}
A \land C \land D \fd F \\
\neg (A \land C \land D) \lor F \\
\neg A \lor \neg C \lor \neg D \lor F
\end{aligned}
$$

c) $$
\begin{aligned}
A \land (D \lor F) \fd C \\
\neg (A \land (D \lor F)) \lor C \\
(\neg A \lor \neg(D \lor F)) \lor C \\
(\neg A \lor (\neg D \land \neg F)) \lor C\\
\neg A \lor C \lor (\neg D \land \neg F) \\
(\neg A \lor C \lor \neg D) \land (\neg A \lor C \lor \neg F) 
\end{aligned}
$$

d) $$
\begin{aligned}
F \eql \neg B \\
(F \fd \neg B) \land (\neg B \fd F) \\
(\neg F \lor \neg B) \land (\neg( \neg B) \lor F)\\
(\neg F \lor \neg B) \land (B \lor F) \\
\end{aligned}
$$

### B. Prove that $E$ is true using resolution.

1. $\displaystyle \frac{(\neg A \lor C \lor \neg D) \land A \land D}{C}$. $C$ 
   is added to KB.
2. $\displaystyle \frac{(\neg A \lor \neg C \lor \neg D \lor \neg F) \land A 
   \land D \land C}{F}$. $F$ is added to KB.
3. $\displaystyle \frac{(\neg F \lor \neg B) \land F}{\neg B}$. $\neg B$ is 
   added to KB.
4. $\displaystyle \frac{(\neg D \lor B \lor E) \land \neg B \land D}{E}$. $E$ 
   is true.

<!--
1. $\displaystyle \frac{A \land (D \lor F) \fd C, A, D}{C}$. $C$ is added to 
   KB.
2. $\displaystyle \frac{A \land C \land D \fd F, A, C, D}{F}$. $F$ is added to 
   KB.
3. $\displaystyle \frac{F \eql \neg B, F}{\neg B}$. $\neg B$ is added to KB.
4. $\displaystyle \frac{D \land \neg B, D, \neg B}{E}$. $E$ is true.
-->

## First-Order Logic Quantifiers

### Translate sentences into FOL:

a) $\forall y, Crust(y) \land GoesWith(Peperoni, y)$.
b) $\exists x, Topping(x) \land GoesWith(x, Stuffed)$.
c) $\forall y \exists \; x \; Topping(x) \land Crust(y) \land
GoesWith(x,y)$.

### Translate FOL into English:

d) Ann does not like curst mushrooms no matter what topping comes with
it.
e) Everyone likes thin pizza with some topping.
f) There are some people love thick pizza no matter what topping comes
with it.

## First-Order Logic Semantics

a) $WorthMore(quarter, penny) \land WorthMore(quarter, Nickel) \land
WorthMore(quarter, dime)$.
b) $WorthMore(quarter, penny) \land WorthMore(quarter, nickel) \land
WorthMore(quarter, dime) \land penny \neq nickel \land nickel \neq
dime \land penny \neq dime \land \forall \; x \; UScoins(x) \land x \neq
quarter \land x \neq penny \land x \neq nickel \land x \neq dime
\land WorthMore(x, quarter)$.

## Unification

a) $x = A, y = B, z = B$.
b) Fail. To unify this two expressions, $x,y$ need to satisfy: 
	- $y = G(x,x)$ and $y = G(A,B)$.
Since there does not exist $x$ such that $G(x,x) = G(A,B)$ therefore
no $x,y$ can satisfy the above condition.
c) $x = A, y = A$.
d) $x = A, y = A, z = G(B)$.

## Resolution Refutation

1) All wolves howl.
2) Anyone who has cats as pets will not have mice.
3) Anyone who is a light sleeper can’t live near anything that howls.
4) Frank either has cats or lives near wolves.
5) If Frank is a light sleeper, Frank has mice.


### Write all the sentences above in FOL

1) $\forall \; w \; Wolf(w) \fd Howl(w)$.
2) $\forall \; x (\exists y \; Cat(y) \land Have(x,y) \fd \sim (\exists
\; z \; Mouse(z) \land Have(x,z)))$.
3) $\forall \; x \; LS(x) \fd \sim (\exists \; y \; Howl(y) \land
Near(x,y))$.
4) $\exists \; x \; Cat(x) \land Have(Frank, x) \lor \exists \; w \;
Wolf(w) \land Near(Frank, w)$.
5) $LS(Frank) \fd \exists \; m \; Mouse(m) \land Have(Frank, m)$.

### Convert all the FOL to CNF


1) $\sim Wolf(w) \lor Howl(w)$.
2) $\sim Cat(y) \lor \sim Have(x,y) \lor \sim Mouse(z) \lor \sim
Have(x,z)$.
3) $\sim LS(x) \lor \sim Howl(y) \lor \sim Near(x,y)$.
4) $(Cat(X_1) \lor Wolf(W_0)) \land (Cat(X_1) \lor Near(Frank, W_0))
\land (Have(Frank, X_1) \lor Wolf(W_0)) \land (Have(Frank, X_1) \lor
Near(Frank, W_0))$.
5) $(\sim LS(Frank) \lor Mouse(M)) \land (\sim LS(Frank) \lor Have(Frank,M))$.

### Prove "Frank is not a light sleeper" using Resolution Refutation.

1) Convert "Frank is not a light sleeper" to CNF: $\sim LS(Frank)$. 
2) Add $LS(Frank)$ to KB and try to find controversy.
3) $\displaystyle \frac{ (\sim LS(F) \lor Mouse(M)) \land LS(Frank)
}{Mouse(M)}$. Add $Mouse(M)$ to KB. $\displaystyle \frac{ (\sim LS(F) \lor
Have(Frank,M)) \land LS(Frank) }{Have(Frank,M)}$. Add $Have(Frank,M)$
to KB.
4) $\displaystyle \frac{(\sim Cat(y) \lor \sim Have(x,y) \lor \sim
Mouse(z) \lor \sim Have(x,z)) \land Mouse(M) \land Have(Frank,M)}{\sim
Cat(y) \lor \sim Have(Frank, y)}$. Add $\sim Cat(y) \lor \sim Have(Frank, y)$ to KB. 

5) $\displaystyle \frac{(Cat(X_1) \lor Wolf(W_0)) \land (\sim Cat(y)
\lor \sim Have(Frank,y))}{Wolf(W_0) \lor \sim Have(Frank,X_1)}$. Add
$Wolf(W_0) \lor \sim Have(Frank,X_1)$ to KB. 

6) $\displaystyle \frac{(Have(Frank,X_1) \lor Near(Frank, W_0)) \land
(Wolf(W_0) \lor \sim Have(Frank,X_1))
}{Near(Frank,W_0) \lor
Wolf(W_0)}$. Add $Near(Frank,W_0) \lor Wolf(W_0)$ to KB.

7) $\displaystyle \frac{ (\sim Wolf(w) \lor Howl(w)) \land (Near(Frank,W_0) \lor
Wolf(W_0)) }{Near(Frank,W_0) \lor Howl(w)}$. Add $Near(Frank,W_0) \lor
Howl(wW_0$ to KB.

8) $\displaystyle \frac{ (\sim LS(x) \lor \sim Howl(y) \lor \sim Near(x,y)) \land
(Near(Frank,W_0) \lor Howl(W_0)) }{\sim LS(Frank)}$. $\sim LS(Frank)$
contradict with our assumption $LS(Frank)$. Therefore Frank is not a
light sleeper.
<!--
6) $\displaystyle \frac{(Cat(X
_1) \lor Near(Frank,W_0)) \land Cat(y)}{Near(Frank, W_0))}$. Add $Near(Frank,W_0)$ to KB.
6) $\displaystyle \frac{(\sim Wolf(W_1) \lor Howl(W_1)) \land W$
-->



