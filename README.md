# Calculadora de IMC Mobile (Kotlin) 🚀

Aplicativo Android nativo desenvolvido para calcular o Índice de Massa Corporal (IMC) com precisão, utilizando critérios diferenciados para crianças, adolescentes e adultos.

## Destaques do Projeto
- **Lógica Eficiente (Clean Code):** Implementação de uma função de cálculo abstraída para evitar repetição de código - através do `Don't Repeat YourSelf` (DRY).
- **Segurança de Dados:** Uso de `toDoubleOrNull()` para evitar crashes por entradas inválidas.
- **Suporte Multifaixa:** Baseado nas tabelas de percentis da OMS para idades de 6 a 18 anos e classificação padrão para adultos.

## Tecnologias Utilizadas
- **Linguagem:** Kotlin
- **Interface:** XML (ConstraintLayout)
- **Ferramenta:** Android Studio

## Interface
O design foi focado em simplicidade e usabilidade, utilizando componentes nativos como RadioGroups para seleção de sexo e EditTexts numéricos.

---
Desenvolvido por Kayck Arcanjo como parte de estudos em ADS