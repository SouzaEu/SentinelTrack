-- Bloco PL/SQL 1: Consulta de Incidentes com Joins, Agrupamento e Ordenação
DECLARE
    v_titulo VARCHAR2(100);
    v_status VARCHAR2(20);
    v_nome_usuario VARCHAR2(100);
    v_nome_local VARCHAR2(100);
    v_qtd_ativos NUMBER;
    
    CURSOR c_incidentes IS
        SELECT i.ds_titulo, i.ds_status, u.nm_usuario, l.nm_local, COUNT(ia.id_ativo) as qtd_ativos
        FROM tb_incidente i
        JOIN tb_usuario u ON i.id_usuario = u.id_usuario
        JOIN tb_local l ON i.id_local = l.id_local
        LEFT JOIN tb_incidente_ativo ia ON i.id_incidente = ia.id_incidente
        GROUP BY i.ds_titulo, i.ds_status, u.nm_usuario, l.nm_local
        ORDER BY COUNT(ia.id_ativo) DESC, i.ds_titulo;
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== RELATÓRIO DE INCIDENTES POR QUANTIDADE DE ATIVOS AFETADOS ===');
    DBMS_OUTPUT.PUT_LINE('Título | Status | Usuário | Local | Qtd Ativos');
    DBMS_OUTPUT.PUT_LINE('----------------------------------------------------------');
    
    OPEN c_incidentes;
    LOOP
        FETCH c_incidentes INTO v_titulo, v_status, v_nome_usuario, v_nome_local, v_qtd_ativos;
        EXIT WHEN c_incidentes%NOTFOUND;
        
        DBMS_OUTPUT.PUT_LINE(v_titulo || ' | ' || v_status || ' | ' || v_nome_usuario || ' | ' || v_nome_local || ' | ' || v_qtd_ativos);
    END LOOP;
    CLOSE c_incidentes;
END;
/

-- Bloco PL/SQL 2: Consulta de Alertas com Joins, Agrupamento e Ordenação
DECLARE
    v_titulo_alerta VARCHAR2(100);
    v_prioridade NUMBER;
    v_status VARCHAR2(20);
    v_titulo_incidente VARCHAR2(100);
    v_count NUMBER;
    
    CURSOR c_alertas IS
        SELECT a.ds_titulo, a.nr_prioridade, a.ds_status, i.ds_titulo as titulo_incidente, COUNT(a.id_alerta) OVER (PARTITION BY a.ds_tipo) as count_por_tipo
        FROM tb_alerta a
        JOIN tb_incidente i ON a.id_incidente = i.id_incidente
        GROUP BY a.ds_titulo, a.nr_prioridade, a.ds_status, i.ds_titulo, a.ds_tipo, a.id_alerta
        ORDER BY a.nr_prioridade, a.ds_status;
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== RELATÓRIO DE ALERTAS POR PRIORIDADE E STATUS ===');
    DBMS_OUTPUT.PUT_LINE('Título Alerta | Prioridade | Status | Incidente | Qtd por Tipo');
    DBMS_OUTPUT.PUT_LINE('----------------------------------------------------------');
    
    OPEN c_alertas;
    LOOP
        FETCH c_alertas INTO v_titulo_alerta, v_prioridade, v_status, v_titulo_incidente, v_count;
        EXIT WHEN c_alertas%NOTFOUND;
        
        DBMS_OUTPUT.PUT_LINE(v_titulo_alerta || ' | ' || v_prioridade || ' | ' || v_status || ' | ' || v_titulo_incidente || ' | ' || v_count);
    END LOOP;
    CLOSE c_alertas;
END;
/

-- Bloco PL/SQL 3: Consulta com valores da linha atual, anterior e próxima
DECLARE
    v_id_atual NUMBER;
    v_titulo_atual VARCHAR2(100);
    v_titulo_anterior VARCHAR2(100);
    v_titulo_proximo VARCHAR2(100);
    v_contador NUMBER := 0;
    
    CURSOR c_incidentes IS
        SELECT id_incidente, ds_titulo
        FROM tb_incidente
        ORDER BY nr_severidade, dt_ocorrencia;
        
    TYPE t_incidentes IS TABLE OF c_incidentes%ROWTYPE;
    v_incidentes t_incidentes;
BEGIN
    OPEN c_incidentes;
    FETCH c_incidentes BULK COLLECT INTO v_incidentes;
    CLOSE c_incidentes;
    
    DBMS_OUTPUT.PUT_LINE('=== RELATÓRIO DE INCIDENTES COM LINHA ANTERIOR E PRÓXIMA ===');
    DBMS_OUTPUT.PUT_LINE('ID | Título Atual | Título Anterior | Título Próximo');
    DBMS_OUTPUT.PUT_LINE('----------------------------------------------------------');
    
    FOR i IN 1..v_incidentes.COUNT LOOP
        v_id_atual := v_incidentes(i).id_incidente;
        v_titulo_atual := v_incidentes(i).ds_titulo;
        
        -- Título anterior
        IF i = 1 THEN
            v_titulo_anterior := 'Vazio';
        ELSE
            v_titulo_anterior := v_incidentes(i-1).ds_titulo;
        END IF;
        
        -- Título próximo
        IF i = v_incidentes.COUNT THEN
            v_titulo_proximo := 'Vazio';
        ELSE
            v_titulo_proximo := v_incidentes(i+1).ds_titulo;
        END IF;
        
        DBMS_OUTPUT.PUT_LINE(v_id_atual || ' | ' || v_titulo_atual || ' | ' || v_titulo_anterior || ' | ' || v_titulo_proximo);
        
        v_contador := v_contador + 1;
        EXIT WHEN v_contador >= 5; -- Limitar a 5 linhas conforme solicitado
    END LOOP;
END;
/
