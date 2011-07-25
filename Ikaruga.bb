; Jeremy Vercillo
; Fairfax Collegiate
; 7/20/11  ; Ikaruga

; Seeds RNG
SeedRnd MilliSecs()

; Starts music
musicchannel = PlayMusic("music.mp3")

; Game variables
level = 0
lives = 25
energy = 0
score = 0

; Polarity variables
Const WHITE = 0
Const BLACK = 1
Const BOSS = 2

; Bullet type - used by both player and enemy
Type Bullet
	Field image, x, y, dx, dy, polarity, owner$
End Type

; Enemy type
Type Enemy
	Field image, x, y, dx, dy, polarity, rate, health
End Type

; Initializes ship's variables
x = 475
y = 600
polarity = WHITE
              
; Set up graphics framework 
Graphics 1000, 700, 32, 2
SetBuffer BackBuffer()

; loads backgroud images 
bg1y = -Rand(4420)
bg2y = -Rand(1348)
bg3y = -Rand(1348)
bg1 = LoadImage("background1.bmp")
bg2 = LoadImage("background3.bmp") 

; loads player sprite images
blackship = LoadImage("ikarugablack.bmp")
whiteship = LoadImage("ikarugawhite.bmp")
playership = whiteship
blackshield = LoadImage("blackshield.bmp")
whiteshield = LoadImage("whiteshield.bmp")
playershield = whiteshield
blackbullet = LoadImage("blackbullet.bmp")
whitebullet = LoadImage("whitebullet.bmp")

; loads enemy sprite images
blackenemy = LoadImage("blackenemy.bmp")
whiteenemy = LoadImage("whiteenemy.bmp")
blackenemybullet = LoadImage("enemybulletblack.bmp")
whiteenemybullet = LoadImage("enemybulletwhite.bmp")

; loads boss sprite images
bossenemy = LoadImage("bossenemy.bmp")

; game loop
While lives > 0	
	; draws/scrolls backgrounds
	DrawImage bg1, -12, bg1y
	DrawImage bg1, -12, bg1y - 5120
	DrawImage bg2, -524, bg2y
	DrawImage bg2, -524, bg2y - 2048
	DrawImage bg2, 0, bg3y
	DrawImage bg2, 0, bg3y - 2048
	bg1y = bg1y + 1
	If bg1y > 700
		bg1y = bg1y - 5120
	EndIf
	bg2y = bg2y + 3
	If bg2y > 700
		bg2y = bg2y - 2048
	EndIf
	bg3y = bg3y + 5
	If bg3y > 700
		bg3y = bg3y - 2048
	EndIf
	Text 0, 0, "Lives:"
	Text 50, 0, lives
	Text 0, 15, "Energy:"
	Text 60, 15, energy
	Text 0, 30, "Score:"
	Text 50, 30, score
	Text 0, 45, "Level:"
	Text 50, 45, level

	; if enemies are gone, levels up
	enemiesleft = 0
	For enemy.Enemy = Each Enemy
		enemiesleft = enemiesleft + 1
	Next
	If enemiesleft = 0
		level = level + 1

		; loads first level
		If level Mod 5 = 1
			For xcount = 0 To 9
				For ycount = 0 To 3
					enemy.Enemy = New Enemy
					If xcount Mod 2 = 0
						enemy\image = blackenemy
						enemy\polarity = BLACK
					ElseIf xcount Mod 2 = 1
						enemy\image = whiteenemy
						enemy\polarity = WHITE
					EndIf
					enemy\x = 100*xcount + 25
					enemy\y = 60*ycount + 75
					enemy\dx = 0
					enemy\dy = 0
					enemy\rate = 10
					enemy\health = 4 + 4 * Int(level/5)
				Next
			Next
		EndIf
		
		; loads second level
		If level Mod 5 = 2
			For xcount = 0 To 9
				For ycount = 0 To 3
					enemy.Enemy = New Enemy
					If (xcount+ycount) Mod 2 = 0
						enemy\image = blackenemy
						enemy\polarity = BLACK
					ElseIf (xcount+ycount) Mod 2 = 1
						enemy\image = whiteenemy
						enemy\polarity = WHITE
					EndIf
					enemy\x = 100*xcount + 25
					enemy\y = 60*ycount + 75
					enemy\dx = 0
					enemy\dy = 0
					enemy\rate = 10
					enemy\health = 4 + 4 * Int(level/5)
				Next
			Next			
		EndIf
		
		; loads third level
		If level Mod 5 = 3
			For xcount = 0 To 4
				enemy.Enemy = New Enemy
				enemy\image = blackenemy
				enemy\polarity = BLACK
				enemy\x = 1100 + 60*xcount
				enemy\y = 75
				enemy\dx = -3
				enemy\dy = 0
				enemy\rate = 10
				enemy\health = 4 + 4 * Int(level/5)
				
				enemy.Enemy = New Enemy
				enemy\image = whiteenemy
				enemy\polarity = WHITE
				enemy\x = -250 - 60*xcount
				enemy\y = 150
				enemy\dx = 3
				enemy\dy = 0
				enemy\rate = 10
				enemy\health = 4 + 4 * Int(level/5)

				enemy.Enemy = New Enemy
				enemy\image = blackenemy
				enemy\polarity = BLACK
				enemy\x = 1300 + 60*xcount
				enemy\y = 225
				enemy\dx = -3
				enemy\dy = 0
				enemy\rate = 10
				enemy\health = 4 + 4 * Int(level/5)
				
				enemy.Enemy = New Enemy
				enemy\image = whiteenemy
				enemy\polarity = WHITE
				enemy\x = -450 - 60*xcount
				enemy\y = 300
				enemy\dx = 3
				enemy\dy = 0
				enemy\rate = 10
				enemy\health = 4 + 4 * Int(level/5)
			Next
			
			For xcount = 0 To 9
				enemy.Enemy = New Enemy
				enemy\image = blackenemy
				enemy\polarity = BLACK
				enemy\x = 25 + 100*xcount
				enemy\y = -1050
				enemy\dx = 0
				enemy\dy = 2
				enemy\rate = 10
				enemy\health = 4 + 4 * Int(level/5)
				
				enemy.Enemy = New Enemy
				enemy\image = whiteenemy
				enemy\polarity = WHITE
				enemy\x = 25 + 100*xcount
				enemy\y = -1125
				enemy\dx = 0
				enemy\dy = 2
				enemy\rate = 10
				enemy\health = 4 + 4 * Int(level/5)
				
			Next
		EndIf

		; loads fourth level
		If level Mod 5 = 4
			For count = 0 To 19
				enemy.Enemy = New Enemy
				If count Mod 2 = 0
					enemy\image = blackenemy
					enemy\polarity = BLACK
				ElseIf count Mod 2 = 1
					enemy\image = whiteenemy
					enemy\polarity = WHITE
				EndIf
				enemy\x = 800 + 50*count
				enemy\y = -100 - 50*count
				enemy\dx = -1
				enemy\dy = 2
				enemy\rate = 10
				enemy\health = 4 + 4 * Int(level/5)
				
				enemy.Enemy = New Enemy
				If count Mod 2 = 0
					enemy\image = whiteenemy
					enemy\polarity = WHITE
				ElseIf count Mod 2 = 1
					enemy\image = blackenemy
					enemy\polarity = BLACK
				EndIf
				enemy\x = 150 - 50*count
				enemy\y = -100 - 50*count
				enemy\dx = 1
				enemy\dy = 2
				enemy\rate = 10
				enemy\health = 4 + 4 * Int(level/5)
			Next
		EndIf
		
		; loads fifth level (boss)
		If level Mod 5 = 0
			enemy.Enemy = New Enemy
			enemy\image = bossenemy
			enemy\polarity = BOSS
			enemy\x = 425
			enemy\y = 50
			enemy\dx = 0
			enemy\dy = 0
			enemy\rate = 100 ; doesn't matter
			enemy\health = 1000 * Int(level/5)
		EndIf
	EndIf

	; bullet limiter
	recoil = recoil - 1

	; Fires when space is pressed
	If KeyDown(57) And recoil <= 0
		bullet.Bullet = New Bullet
		bullet\x = x + 25 - 10 - 10
		bullet\y = y + 25 - 20
		bullet\dx = 0
		bullet\dy = -10
		bullet\polarity = polarity
		If polarity = WHITE
			bullet\image = whitebullet
		ElseIf polarity = BLACK
			bullet\image = blackbullet
		EndIf
		bullet\owner = "player"

		bullet.Bullet = New Bullet
		bullet\x = x + 25 - 10 + 10
		bullet\y = y + 25 - 20
		bullet\dx = 0
		bullet\dy = -10
		bullet\polarity = polarity
		If polarity = WHITE
			bullet\image = whitebullet
		ElseIf polarity = BLACK
			bullet\image = blackbullet
		EndIf
		bullet\owner = "player"

		recoil = 5
	EndIf
	
	; Flips polarity if shift is hit
	If KeyHit(54) Or KeyHit(42)
		If playership = whiteship
			playership = blackship
			playershield = blackshield
			polarity = BLACK
		ElseIf playership = blackship
			playership = whiteship
			playershield = whiteshield
			polarity = WHITE
		EndIf
	EndIf

	; uses energy when z is hit
	If KeyHit(44) And energy > 50
		numbullets = 50
		energy = energy - 50
		speed = 5
		For angle = 180 To 360
			bullet.Bullet = New Bullet
			bullet\x = x + 25 - 4
			bullet\y = y + 25 - 4
			bullet\dx = speed*Cos(angle)
			bullet\dy = speed*Sin(angle)
			bullet\polarity = polarity
			If polarity = WHITE
				bullet\image = whiteenemybullet
			ElseIf polarity = BLACK
				bullet\image = blackenemybullet
			EndIf
			bullet\owner = "player"
		Next
	EndIf

	; Moves with arrow keys but stays on screen
	If KeyDown(200) And y > 0 ; up
		y = y - 7
	EndIf
	If KeyDown(208) And y < 650 ; down
		y = y + 7
	EndIf
	If KeyDown(203) And x > 0 ; left
		x = x - 7
	EndIf
	If KeyDown(205) And x < 950 ; right
		x = x + 7
	EndIf
	DrawImage playershield, x, y
	DrawImage playership, x, y

	; Moves and draws all enemies
	For enemy.Enemy = Each Enemy
		flag = 0
		enemy\x = enemy\x + enemy\dx
		enemy\y = enemy\y + enemy\dy
		
		If enemy\x > 1000 And enemy\dx >= 0
			flag = 1
		EndIf
		If enemy\x < -50 And enemy\dx <= 0
			flag = 1
		EndIf
		If enemy\y > 700 And enemy\dy >= 0
			flag = 1
		EndIf
		If enemy\y < -50 And enemy\dy <= 0
			flag = 1
		EndIf
		If enemy\polarity = BOSS
			If Rand(1000) < enemy\rate
				col = Rand(2)
				For angle = 0 To 180
					bullet.Bullet = New Bullet
					If col = 1
						bullet\polarity = WHITE
						bullet\image = whiteenemybullet
					Else
						bullet\polarity = BLACK
						bullet\image = blackenemybullet
					EndIf
					speed = 5
					bullet\x = enemy\x + 75
					bullet\y = enemy\y + 60
					bullet\dx = speed*Cos(angle)
					bullet\dy = speed*Sin(angle)
					bullet\owner = "enemy"
				Next
			EndIf
		Else
			If Rand(1000) < enemy\rate
				bullet.Bullet = New Bullet
				If enemy\polarity = WHITE
					bullet\polarity = WHITE
					bullet\image = whiteenemybullet
				ElseIf enemy\polarity = BLACK
					bullet\polarity = BLACK
					bullet\image = blackenemybullet
				EndIf
				bullet\x = enemy\x + 25 - 4
				bullet\y = enemy\y + 25 - 4
				speed = 2
				angle = Rand(45, 135)
				bullet\dx = speed*Cos(angle)
				bullet\dy = speed*Sin(angle)
				bullet\owner = "enemy"
			EndIf
		EndIf

		DrawImage enemy\image, enemy\x, enemy\y
		If enemy\polarity = BOSS
			Text 900, 0, "Boss HP:"
			Text 965, 0, enemy\health
		EndIf
		If ImagesCollide(enemy\image, enemy\x, enemy\y, 0, playership, x, y, 0)
			lives = lives-1
		EndIf
		If flag
			Delete enemy
		EndIf
	Next

	; Moves and draws all the bullets (friendly and enemy)
	For bullet.Bullet = Each Bullet
		flag = 0
		bullet\x = bullet\x + bullet\dx
		bullet\y = bullet\y + bullet\dy
		DrawImage bullet\image, bullet\x, bullet\y
		If bullet\y < -20 Or bullet\y > 700 Or bullet\x < -20 Or bullet\x > 1000
			flag = 1
		EndIf  
		
		; bullet collision
		If bullet\owner = "player"
			For enemy.Enemy = Each Enemy
				flag2 = 0
				If ImagesCollide(bullet\image, bullet\x, bullet\y, 0, enemy\image, enemy\x, enemy\y, 0)
					If enemy\polarity = bullet\polarity
						enemy\health = enemy\health - 1
					ElseIf Not enemy\polarity = bullet\polarity
						enemy\health = enemy\health - 2
					ElseIf enemy\polarity = BOSS
						enemy\health = enemy\health - 1
					EndIf
					If enemy\health <= 0
						flag2 = 1
					EndIf
					flag = 1
				EndIf
				If flag2
					If enemy\polarity = BOSS
						Delete enemy
						score = score + 1000*Int(level/5)
					Else 
						numbullets = 20
						For bulletcount = 1 To numbullets
							speed = 3
							newbullet.Bullet = New Bullet
							newbullet\x = enemy\x + 25 - 4
							newbullet\y = enemy\y + 25 - 4
							newbullet\dx = speed*Cos(360/numbullets * bulletcount)
							newbullet\dy = speed*Sin(360/numbullets * bulletcount)
							If enemy\polarity = BLACK
								newbullet\image = blackenemybullet
								newbullet\polarity = BLACK
							ElseIf enemy\polarity = WHITE
								newbullet\image = whiteenemybullet
								newbullet\polarity = WHITE
							EndIf
							newbullet\owner$ = "enemy"
						Next
						Delete enemy
						score = score + 100 + 100*Int(level/5)
					EndIf
				EndIf
			Next
		ElseIf bullet\owner = "enemy"
			If (ImagesCollide(bullet\image, bullet\x, bullet\y, 0, playershield, x, y, 0) Or ImagesCollide(bullet\image, bullet\x, bullet\y, 0, playership, x, y, 0)) And bullet\polarity = polarity
				energy = energy + 1
				flag = 1
			ElseIf ImagesCollide(bullet\image, bullet\x, bullet\y, 0, playership, x, y, 0)
				lives = lives-1
				flag = 1
			EndIf
		EndIf
		If flag
			Delete bullet
		EndIf
	Next

	Flip
	Cls	
Wend