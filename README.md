# SkillForge Katas

Exercícios práticos de Java 21 resolvidos via TDD.
Cada kata é uma branch independente com uma classe vazia e testes que você precisa fazer passar.

---

## Como funciona

```
você clona a branch do kata
        ↓
testa → tudo falha (esperado)
        ↓
implementa a classe
        ↓
testa → tudo passa ✅
        ↓
abre PR com sua solução
```

---

## Passo a passo

### 1. Escolha um kata da lista abaixo e clone a branch

```bash
git clone -b kata-007a-template https://github.com/fidelisfelipe/skillforge-katas.git kata-007a
cd kata-007a
```

### 2. Veja o que você precisa implementar

```bash
# Leia os testes — eles descrevem o comportamento esperado
cat src/test/java/com/skillforge/kata/SalesReporterTest.java
```

### 3. Rode os testes (vão falhar — é o objetivo)

```bash
mvn verify
# BUILD FAILURE ← normal, a classe está vazia
```

### 4. Implemente a classe

```bash
# Edite o arquivo:
src/main/java/com/skillforge/kata/SalesReporter.java
```

Adicione os métodos necessários até os testes passarem.

### 5. Confirme que passou

```bash
mvn verify
# BUILD SUCCESS ✅
```

### 6. Envie sua solução

```bash
git checkout -b kata-007a-seuNome-solution
git add .
git commit -m "kata-007a: implement SalesReporter"
git push origin kata-007a-seuNome-solution
```

Abra um Pull Request com o body:
```
heroId: seuNome
```

---

## Katas disponíveis

### Virtual Threads & Concurrency
| Branch | Kata | Dificuldade | XP |
|--------|------|-------------|-----|
| `kata-001a-template` | Virtual Threads: Hello Concurrency | intermediate | 80 |
| `kata-001b-template` | Virtual Threads: Blocking I/O Without Pain | intermediate | 100 |
| `kata-001c-template` | Structured Concurrency com StructuredTaskScope | advanced | 120 |

### Records & Data Classes
| Branch | Kata | Dificuldade | XP |
|--------|------|-------------|-----|
| `kata-002a-template` | Records: Modelo de Domínio Imutável | beginner | 80 |
| `kata-002b-template` | Records: Compact Constructors e Validação | beginner | 90 |
| `kata-002c-template` | Sealed Interfaces + Records: ADT em Java | intermediate | 110 |

### Pattern Matching
| Branch | Kata | Dificuldade | XP |
|--------|------|-------------|-----|
| `kata-003a-template` | Pattern Matching: instanceof sem Casting | intermediate | 90 |
| `kata-003b-template` | Switch Expressions com Guarded Patterns | intermediate | 100 |
| `kata-003c-template` | Record Deconstruction Patterns | advanced | 130 |

### Sealed Classes & Interfaces
| Branch | Kata | Dificuldade | XP |
|--------|------|-------------|-----|
| `kata-004a-template` | Sealed Classes: Payment Type Hierarchy | intermediate | 90 |
| `kata-004b-template` | Sealed Types: Exhaustive Switch | advanced | 110 |

### Text Blocks
| Branch | Kata | Dificuldade | XP |
|--------|------|-------------|-----|
| `kata-005a-template` | Text Blocks: Templates sem Escape Hell | beginner | 60 |

### Switch Expressions
| Branch | Kata | Dificuldade | XP |
|--------|------|-------------|-----|
| `kata-006a-template` | Switch Expressions: Substituir if-else chains | intermediate | 80 |
| `kata-006b-template` | Switch com yield e blocos | intermediate | 90 |

### Stream API
| Branch | Kata | Dificuldade | XP |
|--------|------|-------------|-----|
| `kata-007a-template` | Streams: Collectors e Agrupamento | intermediate | 90 |
| `kata-007b-template` | Streams: flatMap e composição de pipelines | intermediate | 100 |
| `kata-007c-template` | Custom Collector | advanced | 130 |

### Lambdas & Functional Interfaces
| Branch | Kata | Dificuldade | XP |
|--------|------|-------------|-----|
| `kata-008a-template` | Function Composition Pipeline | intermediate | 85 |
| `kata-008b-template` | Custom Functional Interfaces | intermediate | 95 |

### Optional
| Branch | Kata | Dificuldade | XP |
|--------|------|-------------|-----|
| `kata-009a-template` | Optional: Eliminar null checks em cadeia | beginner | 70 |

### Collections & Generics
| Branch | Kata | Dificuldade | XP |
|--------|------|-------------|-----|
| `kata-010a-template` | Generics: Bounded Type Parameters | intermediate | 95 |
| `kata-010b-template` | Collections: Algoritmos e Ordenação | intermediate | 85 |

### Concurrency Classic
| Branch | Kata | Dificuldade | XP |
|--------|------|-------------|-----|
| `kata-011a-template` | CompletableFuture: Pipeline Assíncrono | advanced | 120 |
| `kata-011b-template` | Thread-safe Data Structures | advanced | 110 |

### Java Platform Module System
| Branch | Kata | Dificuldade | XP |
|--------|------|-------------|-----|
| `kata-012a-template` | JPMS: Criar e Consumir um Módulo | intermediate | 100 |

### Exception Handling
| Branch | Kata | Dificuldade | XP |
|--------|------|-------------|-----|
| `kata-013a-template` | Exception Hierarchy: Domínio Financeiro | beginner | 65 |

### String API Moderna
| Branch | Kata | Dificuldade | XP |
|--------|------|-------------|-----|
| `kata-014a-template` | String API: Métodos Modernos de Texto | beginner | 70 |
| `kata-014b-template` | String API: Text Blocks e Formatação | beginner | 80 |

---

## Pré-requisitos

- Java 21+
- Maven 3.9+

Verifique:
```bash
java -version   # openjdk 21 ou superior
mvn -version    # Apache Maven 3.9 ou superior
```
