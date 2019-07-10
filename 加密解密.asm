DATAS SEGMENT
   	TITL DB 	"------------------------------------------------",0AH,0DH
   		  DB	"This is a File encryption and decryption device ",0AH,0DH
   		  DB	"                BY YINGJUN ZHAO                 ",0AH,0DH
   		  DB	"              ZHONGKAI UNIVERSITY               ",0AH,0DH
   		  DB	"                 201420224627                   ",0AH,0DH
   		  DB	"---------------WELCOME TO USE-------------------",0AH,0DH,'$'
    TIP1  DB    "PLESE INPUT THE FILE NAME:",0AH,0DH,'$'
    FAILOPEN	DB	"FAIL TO OPEN THE FILE!",0AH,0DH,'$'
    FAILCREATE	DB	"FAIL TO CREATE THE FILE!",0AH,0DH,'$'
    FAILREAD	DB	"FAIL TO READ THE FILE!",0AH,0DH,'$'
    FAILWRITE	DB	"FAIL TO WRITE THE FILE!",0AH,0DH,'$'
    SUCCESS1	DB	0AH,0DH,"ENCRYPT SUCCESS!",0AH,0DH,'$'
    SUCCESS2	DB	0AH,0DH,"DENCRYPT SUCCESS!",0AH,0DH,'$'
    TIP2		DB	0AH,0DH,"PLESE CHOSE 1.ENCRYPT    2.DENCRYPT",0AH,0DH,'$'
    FILEMAX DB 	20
    FILEACT DB  ?
    FILE1   DB  20	DUP (?)
    HANDLE1 DW	?
    BUF		DB	?
    FILE2   DB  "temp.txt",0
    HANDLE2 DW	?
    CHOSE	DB	?
DATAS ENDS

STACKS SEGMENT
    ;此处输入堆栈段代码
STACKS ENDS

CODES SEGMENT
    ASSUME CS:CODES,DS:DATAS,SS:STACKS
START:
    MOV AX,DATAS
    MOV DS,AX
    MOV ES,AX
    PRINT	MACRO	M				
		PUSH DX
		PUSH AX
		LEA DX,M
		MOV AH,09H
		INT 21H
		POP AX
		POP DX
   	ENDM
   	PRINT TITL
   	PRINT TIP1
   	MOV DX,0				;读文件
   	LEA DX,FILEMAX
   	
   	MOV AH,0AH
   	INT	21H
   	MOV BH,0
   	MOV BL,FILEACT
   	MOV FILE1[BX],0       ;0标识ascii串
   	LEA DX,FILE1
   	MOV AL,00H			;读打开文件
   	MOV AH,3DH
   	INT 21H
   	JNC SUCCESSOPEN
   	PRINT FAILOPEN
   	JMP QUIT				;打开文件失败退出
SUCCESSOPEN:
	MOV HANDLE1,AX   	
   	PRINT TIP2
   	MOV AH,01H
   	INT 21H
   	CMP AL,31H				
	JZ CHOSE1				
	CMP AL,32H				
	JZ CHOSE2				
	JMP QUIT				;选择失败退出
CHOSE1:MOV AL,01H				
	MOV CHOSE,AL
	JMP CREATE 
CHOSE2:MOV AL,02H				
	MOV CHOSE,AL
	JMP CREATE
CREATE:LEA DX,FILE2					
	MOV AH,3CH				
	MOV CX,0				
	INT 21H		
	JNC SUCCESSCREATE				
	PRINT FAILCREATE				
	JMP QUIT	   			;创建文件失败退出
SUCCESSCREATE:
	MOV HANDLE2,AX
READ:		
	LEA DX,BUF
	MOV BX,HANDLE1
	MOV CX,1
	MOV AH,3FH
	INT 21H
	JNC SUCCESSREAD
	PRINT FAILREAD			
	JMP QUIT   				;读取文件失败退出
SUCCESSREAD:
	TEST AX,0FFFFH				
	JZ READOVER				;读字节完
	MOV AL,BUF				
	MOV BL,CHOSE				
	CMP BL,01H					
	JNZ DECRYPT
	NOT AL								
	ADD AL,01H				;加密	
	JMP WRITE	
DECRYPT:
	SUB AL,01H				;解密
	NOT AL  	
WRITE:
	MOV BUF,AL
	MOV BX,HANDLE2
	MOV CX,1
	LEA DX,BUF
	MOV AH,40H
	INT 21H
	JMP READ  	
READOVER:
	MOV BX,HANDLE1				
	MOV AH,3EH					
	INT 21H
		
	MOV BX,HANDLE2				
	MOV AH,3EH					
	INT 21H	
	
	LEA DX,FILE1			;删除原文件
	MOV AH,41H					
	INT 21H
	
	LEA DX,FILE2			;创建的文件重命名
	LEA DI,FILE1
	MOV AH,56H					
	INT 21H
	
	CMP CHOSE,01H
	JNZ SUCCE 
	PRINT SUCCESS1
	JMP QUIT
SUCCE:
	PRINT SUCCESS2
	
   	
QUIT:MOV AH,4CH
    INT 21H
CODES ENDS
    END START









