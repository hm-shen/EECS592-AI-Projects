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

