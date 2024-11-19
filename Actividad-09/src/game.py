import random as rd

class game:
    '''
    Clase game

    Clase que contiene la logica del juego 
    adivinar la palabra
    '''
    def __init__(self):
        '''
        Inicializa el juego con sus parametros
        e imprime en la pantalla los mensajes iniciales

        Parametros:
            - palabra: se elije una palabra aleatoria del
                        archivo words.txt, esta sera la palabra a buscar
            
            - play: es el progreso del juego inicia con '_' correspondiente
                    a cada letra y se llena progresivamente

            -intentos: la cantidad de intentos permitidos sera de 1.5 veces 
                       el tamaño de la palabra a adivinar
            
            - pistas: para mayor dinamismo se elije aleatoriamente la 
                      cantidad de pistas

            - yn: es el estado de solicitud de una pista
        '''
        self.palabra = self.seleccionar_palabra()
        self.play = "_"*len(self.palabra)
        self.intentos = int(len(self.palabra)*1.5)
        self.pistas = rd.randint(1,2)
        self.yn = "n"

        print("Bienvenido al Juego de Adivinanza de Palabras!")
        #print(self.palabra)
        #print(self.play)
        print("La palabra ha sido seleccionada. ¡Comienza a adivinar!")
        
        print("="*15)
        print(f'Palabra: {self.play}')

        '''
        Con todo inicializado empezamos el juego pidiendo una 
        letra y empezar a adivinar
        '''
        letra = input('Adivina una letra: ')
        self.adivinar_letra(letra)

    def seleccionar_palabra(self):
        '''
        Selecciona aleatoriamente una palabra
        del archivo words.txt
        '''

        with open("words.txt", "r")  as file:
            txt = file.read()

            txt = txt.split('\n')

            return rd.choice(txt)
        
    def print_console(self):
        '''
        Imprimir en la consola el estado de la partida

        Se disminuye en uno la cantidad de intentos

        Se verifica si el atributo play contiene '_'
        dentro, de ser así el juego prosigue, de lo contrario
        significa que no hay más palabras por adivinar, entonces
        se termina el juego
        '''

        if '_' not in self.play:
            print("Felicidades, encontraste la palabra")
            exit()

        print(f'Tienes {self.intentos} intentos')

        self.intentos = self.intentos - 1

        if self.intentos < 0:
            print(f'Lo siento, has perdido. La palabra era: "{self.palabra}"')
            exit()

        print()
        print("="*15)
        #Escribe el estado de la partida
        print(f'Palabra: {self.play}')

        #Si quedan pistas
        if self.pistas > 0:
            #Se consulta si necesitan una pista
            self.yn = input('¿Necesita una pista? (s/n): ').lower()
            #Si se necesita
            if self.yn == 's':
                #Disminuyen las pistas
                self.pistas = self.pistas - 1
                #Se busca la letra de pista
                letra = self.dar_pista()
                #Se añade la letra 
                self.adivinar_letra(letra)
        else:
            #Se notifica la falta de pistas
            print("No te quedan mas pistas")
        
        #En caso no se quieran pistas o no queden
        #se pide una letra y se juega
        letra = input('Adivina una letra: ')
        self.adivinar_letra(letra)
        
    def adivinar_letra(self, letra):
        '''
        Metodo central del juego, se maneja la 
        logica de busqueda y llenado de la palabra a 
        buscar.
        '''

        #Si la letra esta en la palabra buscada
        if letra in self.palabra:
            #Se imprime una frase dependiendo 
            #de si se pidio una pista
            if self.yn == "s":
                print(f'Pista: La letra "{letra}" esta en la palabra')
                self.yn = "n"
            else:
                print(f'¡Correcto! La letra "{letra}" esta en la palabra.')
            
            #Transformamos play en una lista
            l = list(self.play)
            
            #Buscamos las coincidencias
            for idx, item in enumerate(self.palabra):
                #Si se encuentra la coincidencia
                #se remplaza en play por esa letra
                if item == letra:
                    l[idx] = letra

            #volvemos el nuevo play
            self.play = ''.join(l)

        #Si la letra no se encuentra se advierte al usuario
        else:
            print(f'¡Ups! La letra "{letra}" no esta en la palabra.')

        print("="*15)
        #Volvemos a la consola para seguir jugando
        self.print_console()

    def dar_pista(self):
        '''
        Se elije aleatoriamente la palabra que 
        se dara como pista
        '''
        letra = rd.choice(self.palabra)

        while letra in self.play:
            letra = rd.choice(self.palabra)

        return letra
