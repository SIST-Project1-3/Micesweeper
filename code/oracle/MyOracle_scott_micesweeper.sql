-- ��� ���̺� ����
CREATE TABLE MEMBER(
    ID  VARCHAR2(20) CONSTRAINT MEMBER_PK PRIMARY KEY, -- �⺻Ű
    PW VARCHAR(20) NOT NULL, -- �н������ �ݵ�� �Է�
    WIN NUMBER(5) DEFAULT 0 NOT NULL, -- �¸�Ƚ���� �⺻�� 0, �ݵ�� �Է�
    LOSE NUMBER(5) DEFAULT 0 NOT NULL, -- �й�Ƚ���� �⺻�� 0, ���ϵ���
    RDATE DATE DEFAULT SYSDATE NOT NULL -- ������ڴ� �⺻�� SYSDATE, �ݵ�� �Է�
);

-- �Խñ� ��ȣ ������ ����
CREATE SEQUENCE BOARD_NO_SEQ
START WITH 1
INCREMENT BY 1;

-- �Խ��� ���̺� ����
CREATE TABLE BOARD(
 NO NUMBER(10)  CONSTRAINT BOARD_PK PRIMARY KEY , -- NO�� �Խñ� ��ȣ�̸�, ����� �������� �̿��Ͽ� �۹�ȣ�� ������.
 TITLE VARCHAR(40) NOT NULL, -- �� ����. �ݵ�� �Է�
 CONTENT VARCHAR(1000) NOT NULL, -- �� ���� 1000�� ����. �ݵ�� �Է�
 ID VARCHAR(20) NOT NULL, -- �ۼ��� ID �ݵ�� �Է�
 VIEWCOUNT NUMBER(5) DEFAULT 0 NOT NULL, -- ��ȸ�� �⺻�� 0 
 WDATE DATE DEFAULT SYSDATE NOT NULL, -- �ۼ����� �⺻�� SYSDATE 
 CONSTRAINT FK_BOARD FOREIGN KEY (ID) REFERENCES MEMBER(ID) -- �ۼ��� �ܷ�Ű ����
);


-- ���� ����
INSERT INTO MEMBER VALUES('test', '1234', 0, 0, SYSDATE);
-- ���� ��� Ȯ��
SELECT * FROM MEMBER;
-- �Խñ� �ۼ�
INSERT INTO BOARD VALUES(BOARD_NO_SEQ.NEXTVAL, '����', '����', 'test', 0, SYSDATE);
-- �Խñ� ���
SELECT * FROM BOARD ORDER BY NO DESC;