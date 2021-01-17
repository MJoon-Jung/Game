import pygame

pygame.init()

screen_width = 1024
screen_height = 626
screen = pygame.display.set_mode((screen_width, screen_height))
clock = pygame.time.Clock()

background = pygame.image.load(
    "D:/programes/py_workspace\Shooting/background/background_image.jpg")


character = pygame.image.load(
    "D:/programes/py_workspace/Shooting/c1.png")
character_size = character.get_rect().size
character_width = character_size[0]
character_height = character_size[1]
character_pos_x = screen_width / 2 - character_width/2
character_pos_y = screen_height - character_height

enermy = pygame.image.load(
    "D:/programes/py_workspace/Shooting/enermy.png")
enermy_size = enermy.get_rect().size
enermy_width = enermy_size[0]
enermy_height = enermy_size[1]
enermy_pos_x = screen_width / 2 - enermy_width/2
enermy_pos_y = 100


pos_x = 0
pos_y = 0
dt = 0.5

running = True

while running:
    dt = clock.tick(60)
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_RIGHT:
                pos_x += 5
            elif event.key == pygame.K_LEFT:
                pos_x -= 5
            elif event.key == pygame.K_UP:
                pos_y -= 5
            elif event.key == pygame.K_DOWN:
                pos_y += 5

        if event.type == pygame.KEYUP:
            if event.key == pygame.K_LEFT or event.key == pygame.K_RIGHT:
                pos_x = 0
            elif event.key == pygame.K_UP or event.key == pygame.K_DOWN:
                pos_y = 0
    character_pos_x += pos_x
    character_pos_y += pos_y

    if character_pos_x < 0:
        character_pos_x = 0
    elif character_pos_x + character_width > screen_width:
        character_pos_x = screen_width - character_width
    if character_pos_y < 0:
        character_pos_y = 0
    elif character_pos_y + character_height > screen_height:
        character_pos_y = screen_height - character_height

    character_rect = character.get_rect()
    character_rect.left = character_pos_x
    character_rect.top = character_pos_y

    enermy_rect = enermy.get_rect()
    enermy_rect.left = enermy_pos_x
    enermy_rect.top = enermy_pos_y

    if character_rect.colliderect(enermy_rect):
        print("coligion")
        running = False

    screen.blit(background, (0, 0))
    screen.blit(character, (character_pos_x, character_pos_y))
    screen.blit(enermy, (enermy_pos_x, enermy_pos_y))
    pygame.display.update()

pygame.quit()
