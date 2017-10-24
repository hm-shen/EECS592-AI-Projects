---
title: EECS 592 Homework 3
author: Haoming Shen
date: \today
geometry: margin=0.9in
fontsize: 11pt
toc: false
secnumdepth: 0
header-includes:
    - \usepackage{bm}
    - \usepackage{color}
    - \usepackage{fontenc}
    - \usepackage{sansmath}
---

\renewcommand*\familydefault{\sfdefault} 
\newcommand{\reminder}[1]{{\textsf{\textcolor{red}{[question: #1]}}}}
\newcommand{\ans}[1]{{\textsf{\textcolor{blue}{[ans: #1]}}}}
\newcommand{\eql}{\Leftrightarrow}
\newcommand{\fd}{\Rightarrow}
\newcommand{\ie}{i.e.}

\normalfont
\sansmath

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
    2. $M = \neg (A \land B) \lor C = (\neg A \lor \neg B) \lor C =
       S$.

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

h) $(\neg B \fd A) \fd (\neg A \fd \neg B)$ is **false**.
    1. $(\neg B \fd A)$ = $\neg (\neg B) \lor A = B \lor A$; $\neg A \fd \neg B 
       = A \lor \neg B$.
    2. Let $A = 0$, $B = 1$, the left hand side is $B \lor A = 1$, the right 
       hand side is $A \lor \neg B = 0$. Therefore the above implication is 
       false.
    <!--
    2. Since for all models that make $(\neg B \fd A)$ true, $(\neg A \fd B)$ 
       is also true, the above statement is true.
    -->

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

<!--\reminder{check}-->

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

<!--
a) $\forall y, Crust(y) \land GoesWith(Peperoni, y)$.
-->
a) $\forall y, Crust(y) \fd GoesWith(Peperoni, y)$.
b) $\exists x, Topping(x) \land GoesWith(x, Stuffed)$.
c) $\forall y \; \exists \; x \; Topping(x) \fd Crust(y) \land
GoesWith(x,y)$.
<!--
c) $\forall y \; \exists \; x \; Topping(x) \land Crust(y) \land
GoesWith(x,y)$.
-->

### Translate FOL into English:

<!--
d) Ann does not like crust mushrooms no matter what topping comes with
it.
-->
d) As long as the pizza covered with mushrooms crust, Ann does not like it.
e) Everyone likes thin pizza with some topping.
f) There are some people love thick pizza no matter what topping comes
with it.

## First-Order Logic Semantics

a) $WorthMore(quarter, penny) \land WorthMore(quarter, nickel) \land
WorthMore(quarter, dime)$.
b) $WorthMore(quarter, penny) \land WorthMore(quarter, nickel) \land
WorthMore(quarter, dime) \land penny \neq nickel \land nickel \neq
dime \land penny \neq dime \land (\forall \; x \; WorthMore(quarter, x) \fd (x 
= penny \lor x = nickel \lor x = dime))$.
<!--
b) $WorthMore(quarter, penny) \land WorthMore(quarter, nickel) \land
WorthMore(quarter, dime) \land penny \neq nickel \land nickel \neq
dime \land penny \neq dime \land \forall \; x \; UScoins(x) \land x \neq
quarter \land x \neq penny \land x \neq nickel \land x \neq dime
\land WorthMore(x, quarter)$.
-->

## Unification

a) $\{x/A, y/B, z/B\}$.
b) Fail. To unify this two expressions, $x,y$ need to satisfy: 
	- $y = G(x,x)$ and $y = G(A,B)$.
Since there does not exist $x$ such that $G(x,x) = G(A,B)$ therefore
no $x,y$ can satisfy the above condition.
c) $\{x/y, y/A\}$.
d) $\{x/A, y/A, z/G(B)\}$.

## Resolution Refutation

1) All wolves howl.
2) Anyone who has cats as pets will not have mice.
3) Anyone who is a light sleeper can’t live near anything that howls.
4) Frank either has cats or lives near wolves.
5) If Frank is a light sleeper, Frank has mice.


### Write all the sentences above in FOL

1) $\forall \; w \; \text{Wolf}(w) \fd \text{Howl}(w)$.
2) $\forall \; x (\exists y \; \text{Cat}(y) \land \text{Have}(x,y) \fd \sim 
   (\exists
\; z \; \text{Mouse}(z) \land \text{Have}(x,z)))$.
3) $\forall \; x \; \text{LS}(x) \fd \sim (\exists \; y \; \text{Howl}(y) \land
\text{Near}(x,y))$.
4) $\exists \; x \; \text{Cat}(x) \land \text{Have}(\text{Frank}, x) \lor \exists \; w \;
\text{Wolf}(w) \land \text{Near}(\text{Frank}, w)$.
5) $\text{LS}(\text{Frank}) \fd \exists \; m \; \text{Mouse}(m) \land \text{Have}(\text{Frank}, m)$.

### Convert all the FOL to CNF


1) $\sim \text{Wolf}(w) \lor \text{Howl}(w)$.
2) $\sim \text{Cat}(y) \lor \sim \text{Have}(x,y) \lor \sim \text{Mouse}(z) \lor \sim
\text{Have}(x,z)$.
3) $\sim \text{LS}(x) \lor \sim \text{Howl}(y) \lor \sim \text{Near}(x,y)$.
4) $(\text{Cat}(X_1) \lor \text{Wolf}(W_0)) \land (\text{Cat}(X_1) \lor \text{Near}(\text{Frank}, W_0))
\land (\text{Have}(\text{Frank}, X_1) \lor \text{Wolf}(W_0)) \land (\text{Have}(\text{Frank}, X_1) \lor
\text{Near}(\text{Frank}, W_0))$.
5) $(\sim \text{LS}(\text{Frank}) \lor \text{Mouse}(M)) \land (\sim \text{LS}(\text{Frank}) \lor \text{Have}(\text{Frank},M))$.

### Prove "Frank is not a light sleeper" using Resolution Refutation. 

<!--
\reminder{check}
-->

1) Convert "Frank is not a light sleeper" to CNF: $\sim
\text{LS}(\text{Frank})$.

2) Add $\text{LS}(\text{Frank})$ to KB and try to find controversy.

3) $\displaystyle \frac{ (\sim \text{LS}(Frank) \lor \text{Mouse}(M)) \land 
   \text{LS}(\text{Frank})
}{\text{Mouse}(M)}$. Add $\text{Mouse}(M)$ to KB. $\displaystyle \frac{ (\sim \text{LS}(F) \lor
\text{Have}(\text{Frank},M)) \land \text{LS}(\text{Frank}) }{\text{Have}(\text{Frank},M)}$. Add $\text{Have}(\text{Frank},M)$
to KB.

4) $\displaystyle \frac{(\sim \text{Cat}(y) \lor \sim \text{Have}(x,y) \lor \sim
\text{Mouse}(z) \lor \sim \text{Have}(x,z)) \land \text{Mouse}(M) \land \text{Have}(\text{Frank},M)}{\sim
\text{Cat}(y) \lor \sim \text{Have}(\text{Frank}, y)}$. Add $\sim \text{Cat}(y) \lor \sim \text{Have}(\text{Frank}, y)$ to KB. 

<!--
5) $\displaystyle \frac{(\text{Cat}(X_1) \lor \text{Wolf}(W_0)) \land (\sim \text{Cat}(y)
\lor \sim \text{Have}(\text{Frank},y))}{\text{Wolf}(W_0) \lor \sim \text{Have}(\text{Frank},X_1)}$. Add
$\text{Wolf}(W_0) \lor \sim \text{Have}(\text{Frank},X_1)$ to KB. -->

5) $\displaystyle \frac{(\text{Cat}(X_1) \lor \text{Wolf}(W_0)) \land (\sim 
   \text{Cat}(y))}{\text{Wolf}(W_0)}$. Add $\text{Wolf}(W_0)$ to KB. $\{y / 
   X_1\}$. 

6) $\displaystyle \frac{(\text{Have}(\text{Frank},X_1) \lor 
   \text{Near}(\text{Frank}, W_0) \land (\text{Have} (\text{Frank} , 
   X_1))}{\text{Near}(\text{Frank}, W_0)}$ Add $\text{Near}(\text{Frank}, 
   W_0)$.

7) $\displaystyle \frac{(\sim Wolf(w) \lor Howl(w)) \land 
   Wolf(W_0)}{Howl(W_0)}$. Add $Howl(W_0)$ to KB.

8) $\displaystyle \frac{(\sim LS(x) \lor \sim Howl(y) \lor \sim Near(x,y)) 
   \land LS(Frank) \land Howl(W_0) \land Have(Frank, W_0)}{\{\}}$. Result in 
   controversy. Therefore \text{Frank} is not a light sleeper.


<!--
6) $\displaystyle \frac{(\text{Have}(\text{Frank},X_1) \lor \text{Near}(\text{Frank}, W_0)) \land
(\text{Wolf}(W_0) \lor \sim \text{Have}(\text{Frank},X_1))
}{\text{Near}(\text{Frank},W_0) \lor
\text{Wolf}(W_0)}$. Add $\text{Near}(\text{Frank},W_0) \lor \text{Wolf}(W_0)$ to KB.

7) $\displaystyle \frac{ (\sim \text{Wolf}(w) \lor \text{Howl}(w)) \land (\text{Near}(\text{Frank},W_0) \lor
\text{Wolf}(W_0)) }{\text{Near}(\text{Frank},W_0) \lor \text{Howl}(w)}$. Add $\text{Near}(\text{Frank},W_0) \lor
\text{Howl}(wW_0$ to KB.

8) $\displaystyle \frac{ (\sim \text{LS}(x) \lor \sim \text{Howl}(y) \lor \sim \text{Near}(x,y)) \land
(\text{Near}(\text{Frank},W_0) \lor \text{Howl}(W_0)) }{\sim 
\text{LS}(\text{Frank})}$. Add $\sim \text{LS}(\text{Frank})$ to KB.

9) $\displaystyle \frac{ \sim \text{LS}(\text{Frank}) \land 
   \text{LS}(\text{Frank}) }{ \{ \} }$. Therefore \text{Frank} is not a
light sleeper.
-->

<!--
6) $\displaystyle \frac{(Cat(X
_1) \lor \text{Near}(\text{Frank},W_0)) \land Cat(y)}{\text{Near}(\text{Frank}, W_0))}$. Add $\text{Near}(\text{Frank},W_0)$ to KB.
6) $\displaystyle \frac{(\sim \text{Wolf}(W_1) \lor \text{Howl}(W_1)) \land W$
-->
