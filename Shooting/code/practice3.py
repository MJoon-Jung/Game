import pygame

pygame.init()

screen_width = 1024
screen_height = 626
screen = pygame.display.set_mode((screen_width, screen_height))
pygame.display.set_caption("Game")
clock = pygame.time.Clock()

background_image = pygame.image.load(
    "D:\programes/py_workspace/Shooting/background/background_image.jpg")
character_image = pygame.image.load(
    "D:/programes/py_workspace/Shooting/c1.png")
character_size = character_image.get_rect().size
character_width = character_size[0]
character_height = character_size[1]
character_pos_x = screen_width / 2 - character_width/2
character_pos_y = screen_height - character_height


enemy_image = pygame.image.load(
    "D:/programes/py_workspace/Shooting/enermy.png")
enemy_size = enemy_image.get_rect().size
enemy_width = enemy_size[0]
enemy_height = enemy_size[1]
enemy_pos_x = screen_width / 2 - enemy_width/2
enemy_pos_y = 100


pos_x = 0
pos_y = 0
character_speed = 0.5

game_font = pygame.font.Font(None, 40)

# 시작 정보
total_time = 10

start_ticks = pygame.time.get_ticks()

running = True
while running:
    dt = clock.tick(60)
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

        if event.type == pygame.KEYDOWN:
            if(event.key == pygame.K_LEFT):
                pos_x -= character_speed
            elif(event.key == pygame.K_RIGHT):
                pos_x += character_speed
            elif(event.key == pygame.K_UP):
                pos_y -= character_speed
            elif(event.key == pygame.K_DOWN):
                pos_y += character_speed

        if event.type == pygame.KEYUP:
            if event.key == pygame.K_LEFT or event.key == pygame.K_RIGHT:
                pos_x = 0
            elif event.key == pygame.K_UP or event.key == pygame.K_DOWN:
                pos_y = 0

    character_pos_x += pos_x * dt
    character_pos_y += pos_y * dt

    if(character_pos_x < 0):
        character_pos_x = 0
    elif(character_pos_x + character_width > screen_width):
        character_pos_x = screen_width - character_width

    if character_pos_y < 0:
        character_pos_y = 0
    elif character_pos_y + character_height > screen_height:
        character_pos_y = screen_height - character_height

    character_rect = character_image.get_rect()
    character_rect.left = character_pos_x
    character_rect.top = character_pos_y

    enemy_rect = enemy_image.get_rect()
    enemy_rect.left = enemy_pos_x
    enemy_rect.top = enemy_pos_y

    if character_rect.colliderect(enemy_rect):
        print("충돌")
        running = False

    screen.blit(background_image, (0, 0))
    screen.blit(character_image, (character_pos_x, character_pos_y))
    screen.blit(enemy_image, (enemy_pos_x, enemy_height))

    # 타이머
    elapsed_time = (pygame.time.get_ticks() - start_ticks) / 1000

    timer = game_font.render(
        str(int(total_time - elapsed_time)), True, (255, 255, 255))

    timer = game_font.render(
        str(int(total_time - elapsed_time)), True, (255, 0, 0))
    # 출력할 글자, True, 글자 색상
    screen.blit(timer, (35, 35))

    if total_time - elapsed_time <= 0:
        print("타임아웃")
        running = False

    pygame.display.update()

pygame.time.delay(2000)
pygame.quit()
