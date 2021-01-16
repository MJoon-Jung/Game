import pygame

pygame.init()

screen_width = 420
screen_height = 280
screen = pygame.display.set_mode((screen_width, screen_height))

pygame.display.set_caption("Game")

running = True

while running:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False


pygame.quit()
