import random as rd

class game:
    def __init__(self):
        self.palabra = self.seleccionar_palabra()
        self.play = "_"*len(self.palabra)
        self.pistas = rd.randint(1,2)
        self.yn = "n"

        print("Bienvenido al Juego de Adivinanza de Palabras!")
        #print(self.palabra)
        #print(self.play)
        print("La palabra ha sido seleccionada. ¡Comienza a adivinar!")
        
        print("="*15)
        print(f'Palabra: {self.play}')
        letra = input('Adivina una letra: ')
        self.adivinar_letra(letra)

    def seleccionar_palabra(self):
        with open("words.txt", "r")  as file:
            txt = file.read()

            txt = txt.split('\n')

            return rd.choice(txt)
        
    def print_console(self):

        if '_' not in self.play:
            print("Felicidades, encontraste la palabra")
            exit()

        print()
        print("="*15)
        print(f'Palabra: {self.play}')

        if self.pistas > 0:
            self.yn = input('¿Necesita una pista? (s/n): ').lower()
            if self.yn == 's':
                self.pistas = self.pistas - 1
                letra = self.dar_pista()
                self.adivinar_letra(letra)
        else:
            print("No te quedan mas pistas")
            
        letra = input('Adivina una letra: ')
        self.adivinar_letra(letra)
        
    def adivinar_letra(self, letra):

        if letra in self.palabra:
        
            if self.yn == "s":
                print(f'Pista: La letra "{letra}" esta en la palabra')
                self.yn = "n"
            else:
                print(f'¡Correcto! La letra "{letra}" esta en la palabra.')
            
            l = list(self.play)
            
            for idx, item in enumerate(self.palabra):
                if item == letra:
                    l[idx] = letra

            self.play = ''.join(l)

        else:
            print(f'¡Ups! La letra "{letra}" no esta en la palabra.')

        print("="*15)
        self.print_console()

    def dar_pista(self):
        letra = rd.choice(self.palabra)

        while letra in self.play:
            letra = rd.choice(self.palabra)

        return letra
